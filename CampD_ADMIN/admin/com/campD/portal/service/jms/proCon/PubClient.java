/**
 * 
 */
package com.campD.portal.service.jms.proCon;

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
		jedis.publish(channel, message);
	}
	
	// 关闭频道
	public void close(String channel){
		jedis.publish(channel, "quit");
		jedis.del(channel);//
	}
	
}
