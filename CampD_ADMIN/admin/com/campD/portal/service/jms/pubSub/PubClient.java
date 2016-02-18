/**
 * 
 */
package com.campD.portal.service.jms.pubSub;

import java.util.Date;

import com.campD.portal.service.jms.Constants;
import com.campD.portal.util.DateUtil;
import com.campD.portal.util.RedisUtil;

import redis.clients.jedis.Jedis;

/**
 * 非持久化-消息发布端
 * @author Garrett
 *
 */
public class PubClient {

	private Jedis jedis;
	private RedisUtil redisUtil = new RedisUtil(Constants.IP, Constants.PORT);
	
	public PubClient(){
		
		jedis = redisUtil.getResource();
	}
	
	// 发布消息
	public void pub(String channel,String message){
		String time = DateUtil.fmtDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		System.out.println("-------------message publish:" + message + ",channel:" + channel + "..." + time);
		jedis.publish(channel, message);
	}
	
	// 关闭频道
	public void close(String channel){
		jedis.publish(channel, "quit");
		jedis.del(channel);//
	}
	
}
