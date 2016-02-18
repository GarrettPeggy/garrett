/**
 * 
 */
package com.campD.portal.service.jms.proCon;

import java.util.List;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.campD.portal.service.jms.Constants;
import com.campD.portal.util.RedisUtil;

/**
 * 消费者
 * @author Administrator
 *
 */
public class Consumer {
	
	protected Logger logger = Logger.getLogger(getClass());

	private String name;// 当前消费者名称
	private String messageList;
	private Jedis jedis;
	private RedisUtil redisUtil = new RedisUtil(Constants.IP, Constants.PORT);
	
	public Consumer (String messageList, String name){
		this.name = name;
		this.messageList = messageList;
		jedis = redisUtil.getResource();
	}
	
	public void consume(){
		while(true){
			List<String> messages = jedis.brpop(30, messageList);
			logger.info("当前消费的列表->" + messageList + " -- 当前消费者为->" + name + " -- 当前消费的消息内容为：" + messages);
		}
	}
	
}
