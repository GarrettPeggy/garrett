/**
 * 
 */
package com.campD.portal.service.jms.proCon;

import redis.clients.jedis.Jedis;

import com.campD.portal.service.jms.Constants;
import com.campD.portal.util.RedisUtil;

/**
 * 生产者
 * @author Administrator
 *
 */
public class Producer {

	private String messageList;
	private Jedis jedis;
	private RedisUtil redisUtil = new RedisUtil(Constants.IP, Constants.PORT);
	
	public Producer (String messageList){
		this.messageList = messageList;
		jedis = redisUtil.getResource();
	}
	
	public void provide(String message){
		jedis.lpush(messageList, message);
	}
	
}
