/**
 * 
 */
package test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import redis.clients.jedis.JedisPubSub;

import com.campD.portal.service.jms.pPubSub.PPubClient;
import com.campD.portal.service.jms.pPubSub.PSubClient;
import com.campD.portal.service.jms.proCon.Consumer;
import com.campD.portal.service.jms.proCon.Producer;
import com.campD.portal.service.jms.pubSub.MessgeListener;
import com.campD.portal.service.jms.pubSub.PubClient;
import com.campD.portal.service.jms.pubSub.SubClient;
import com.campD.portal.util.JsonHelper;

/**
 * Redis消息队列实现测试
 * @author Garrett
 *
 */
public class RedisJmsTest {
	
	protected static Logger logger = Logger.getLogger(RedisJmsTest.class);

	public static void main(String[] args) throws InterruptedException {
		
		// 非持久化的发布订阅模式测试
		//testPubSub();
		// 持久化的发布订阅模式测试
		//testPPubSub();
		// 生产者消费者模式测试
		testProCom();

	}
	
	/**
	 * 非持久化的发布订阅模式测试
	 * @throws InterruptedException 
	 *
	 */
	private static void testPubSub() throws InterruptedException{
		
		PubClient pubClient = new PubClient();
		final String channel = "pubsub-channel";
		// 以下这两条消息由于是在订阅前发布的，所以后面订阅的订阅者收不到这两条消息
		pubClient.pub(channel, "before1");
		pubClient.pub(channel, "before2");
		Thread.sleep(1000);
		//消息订阅者非常特殊，需要独占链接，因此我们需要为它创建新的链接；
		//此外，jedis客户端的实现也保证了“链接独占”的特性，sub方法将一直阻塞，
		//直到调用listener.unsubscribe方法
		Thread subThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					SubClient subClient = new SubClient();
					logger.info("----------subscribe operation begin-------");
					JedisPubSub listener = new MessgeListener();
					//在API级别，此处为轮询操作，直到unsubscribe调用，才会返回
					subClient.sub(listener, channel);
					logger.info("----------subscribe operation end-------");
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		});
		subThread.start();
		
		int i=0;
		while(i < 10){
			String message = "这是第"+i+"条消息！";
			pubClient.pub(channel, message);
			i++;
			//Thread.sleep(1000);
		}
		
		//被动关闭指示，如果通道中，消息发布者确定通道需要关闭，那么就发送一个“quit”
		//那么在listener.onMessage()中接收到“quit”时，其他订阅client将执行“unsubscribe”操作。
		Thread.sleep(10000);// 10秒后停止订阅
		pubClient.close(channel);
		//此外，你还可以这样取消订阅
		//listener.unsubscribe(channel);
	}
	
	/**
	 * 持久化的发布订阅模式测试
	 * @throws InterruptedException 
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void testPPubSub() throws InterruptedException{
		
		PPubClient pubClient = new PPubClient();  
        final String channel = "pubsub-channel-p";  
        final PSubClient subClient = new PSubClient("subClient-1");
        final PSubClient subClient2 = new PSubClient("subClient-2");
        
        Thread subThread = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                logger.info("----------subscribe1 operation begin-------");  
                //在API级别，此处为轮询操作，直到unsubscribe调用，才会返回  
                subClient.sub(channel);
                logger.info("----------subscribe1 operation end-------");  
            }  
        });  
        subThread.setDaemon(true);  
        subThread.start(); 
        
        Thread subThread1 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                logger.info("----------subscribe2 operation begin-------");  
                //在API级别，此处为轮询操作，直到unsubscribe调用，才会返回  
                subClient2.sub(channel);
                logger.info("----------subscribe2 operation end-------");  
            }  
        });  
        subThread1.setDaemon(true);  
        subThread1.start(); 
        
        Map messageMap = null;
        int i = 0;  
        while(i < 20){
        	messageMap = new HashMap();
        	messageMap.put("id", UUID.randomUUID());
        	messageMap.put("name", "王光华"+i);
        	String message = JsonHelper.parseToJson(messageMap);
            pubClient.pub(channel, message);  
            i++;
            Thread.sleep(100);
        }
        messageMap = null;
        
        //被动关闭指示，如果通道中，消息发布者确定通道需要关闭，那么就发送一个“quit”
  		//那么在listener.onMessage()中接收到“quit”时，其他订阅client将执行“unsubscribe”操作。
  		Thread.sleep(10000);// 10秒后停止订阅
  		pubClient.close(channel);
  		//此外，你还可以这样取消订阅
  		//listener.unsubscribe(channel); 
	}
	
	/**
	 * 生产者消费者模式测试
	 * @throws InterruptedException 
	 *
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void testProCom() throws InterruptedException{

		final Consumer consumer1 = new Consumer("messageList1", "consumer1");
		Thread consumerThread1 = new Thread(new Runnable() { // 消费者1 
            @Override  
            public void run() {  
                logger.info("----------consumer1 operation begin-------");  
                consumer1.consume();
                logger.info("----------consumer1 operation end-------");  
            }  
        });  
		consumerThread1.setDaemon(true);  
		consumerThread1.start();

		final Consumer consumer2 = new Consumer("messageList1", "consumer2");
		Thread consumerThread2 = new Thread(new Runnable() {  // 消费者2 
            @Override  
            public void run() {  
                logger.info("----------consumer2 operation begin-------");  
                consumer2.consume();
                logger.info("----------consumer2 operation end-------");  
            }  
        });  
		consumerThread2.setDaemon(true);  
		consumerThread2.start();

		final Consumer consumer3 = new Consumer("messageList1", "consumer3");
		Thread consumerThread3 = new Thread(new Runnable() {  // 消费者3
            @Override  
            public void run() {  
                logger.info("----------consumer3 operation begin-------");  
                consumer3.consume();
                logger.info("----------consumer3 operation end-------");  
            }  
        });  
		consumerThread3.setDaemon(true);  
		consumerThread3.start();
		
		final Consumer consumer4 = new Consumer("messageList1", "consumer4");
		Thread consumerThread4 = new Thread(new Runnable() {  // 消费者4
            @Override  
            public void run() {  
                logger.info("----------consumer4 operation begin-------");  
                consumer4.consume();
                logger.info("----------consumer4 operation end-------");  
            }  
        });  
		consumerThread4.setDaemon(true);  
		consumerThread4.start();

		final Producer producer1 = new Producer("messageList1");
		final Producer producer2 = new Producer("messageList1");
		Map messageMap = null;
		int i = 0; 
		long starTime=System.currentTimeMillis();
		logger.info("-----------开始产生消息---------------");
        while(i < 1000){
        	messageMap = new HashMap();
        	messageMap.put("id", UUID.randomUUID());
        	
        	messageMap.put("name", "王光华1"+i);
        	String message1 = JsonHelper.parseToJson(messageMap);
        	producer1.provide(message1);
        	
        	messageMap.put("name", "王光华2"+i);
        	String message2 = JsonHelper.parseToJson(messageMap);
        	producer2.provide(message2);
            i++;
            // 这里让它停顿是为了模仿日常请求
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//停顿0.1秒
        }
        logger.info("-----------结束产生消息---------------");
        long endTime=System.currentTimeMillis();
        logger.info(endTime - starTime);
		
	}

}
