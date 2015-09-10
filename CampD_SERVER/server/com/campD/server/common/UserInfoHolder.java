package com.campD.server.common;

import com.campD.server.model.UserInfo;

/**
 * 用户信息holder，用户后台程序使用
 * @author Rain
 *
 */
public class UserInfoHolder {
	
	public static ThreadLocal<UserInfo> userInfoLocal = new ThreadLocal<UserInfo>();
	
	public static void set(UserInfo userInfo){
		userInfoLocal.set(userInfo);
	}
	
	/**
	 * 获取用户信息，是否能为空
	 * @param canNull
	 * @return
	 */
	public static UserInfo get(boolean canNull){
		UserInfo userInfo = userInfoLocal.get();
		if(userInfo==null && !canNull){
			throw new RuntimeException("login userInfo is null");
		}
		return userInfo;
	}
	
	public static UserInfo get(){
		return get(true);
	}
	
	
	public static void remove(){
		userInfoLocal.remove();
	}
	
	
	
}
