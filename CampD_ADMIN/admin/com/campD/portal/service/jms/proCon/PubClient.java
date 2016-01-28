/**
 * 
 */
package com.campD.portal.service.jms.proCon;

import java.util.Date;

import com.campD.portal.util.DateUtil;

import redis.clients.jedis.Jedis;

/**
 * 消息发布端
 * @author Garrett
 *
 */
public class PubClient {

	private Jedis jedis;
	
	public PubClient(String host,int port){
		jedis = new Jedis(host,port);
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
