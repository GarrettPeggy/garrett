/**
 * 
 */
package com.campD.portal.service.jms.proCon;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * 消息订阅端
 * @author Garrett
 *
 */
public class SubClient {

	private Jedis jedis;//
	
	public SubClient(String host,int port){
		jedis = new Jedis(host,port);
	}
	
	public void sub(JedisPubSub listener,String channel){
		jedis.subscribe(listener, channel);
		//此处将会阻塞，在client代码级别为JedisPubSub在处理消息时，将会“独占”链接
		//并且采取了while循环的方式，侦听订阅的消息
	}
	
}
