/**
 * 
 */
package com.campD.portal.service.jms.proCon;

import java.util.Date;

import com.campD.portal.util.DateUtil;

import redis.clients.jedis.JedisPubSub;

/**
 * 订阅者消息处理器
 * 非持久化订阅
 * @author Garrett
 *
 */
public class MessgeListener extends JedisPubSub {
	
	@Override
	public void onMessage(String channel, String message) {
		String time = DateUtil.fmtDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		System.out.println("message receive:" + message + ",channel:" + channel + "..." + time);
		//此处我们可以取消订阅
		if(message.equalsIgnoreCase("quit")){
			this.unsubscribe(channel);
		}
	}

}
