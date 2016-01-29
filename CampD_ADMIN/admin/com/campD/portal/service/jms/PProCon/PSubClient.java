/**
 * 
 */
package com.campD.portal.service.jms.PProCon;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import com.campD.portal.service.jms.Constants;
import com.campD.portal.util.RedisUtil;

/**
 * 持久化-消息订阅端
 * @author Administrator
 *
 */
public class PSubClient {

	 private Jedis jedis; 
	 private RedisUtil redisUtil = new RedisUtil(Constants.IP, Constants.PORT);
     private JedisPubSub listener;//单listener  
      
     public PSubClient(String clientId){  
        jedis = redisUtil.getResource(); 
        listener = new PMessageListener(clientId, redisUtil.getResource());  
     }  
      
     public void sub(String channel){  
        jedis.subscribe(listener, channel);  
     }  
      
     public void unsubscribe(String channel){  
        listener.unsubscribe(channel);  
     }
	
}
