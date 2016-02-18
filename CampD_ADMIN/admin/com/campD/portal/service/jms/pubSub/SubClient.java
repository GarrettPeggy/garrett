/**
 * 
 */
package com.campD.portal.service.jms.pubSub;

import com.campD.portal.service.jms.Constants;
import com.campD.portal.util.RedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * 非持久化-消息订阅端
 * @author Garrett
 *
 */
public class SubClient {

	private Jedis jedis;
	private RedisUtil redisUtil = new RedisUtil(Constants.IP, Constants.PORT);
	
	public SubClient(){
		jedis = redisUtil.getResource();
	}
	
	public void sub(JedisPubSub listener,String channel){
		jedis.subscribe(listener, channel);
		//此处将会阻塞，在client代码级别为JedisPubSub在处理消息时，将会“独占”链接
		//并且采取了while循环的方式，侦听订阅的消息
	}
	
}
