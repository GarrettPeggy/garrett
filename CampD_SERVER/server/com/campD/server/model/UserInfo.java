package com.campD.server.model;

import java.io.Serializable;

/**
 * 用户信息dto
 * 
 * @author Rain
 * 
 */
public class UserInfo implements Serializable{
    
	private static final long serialVersionUID = 2195119891567279998L;

	private String userId;
    private String mdn;
    private String userName;
    private String email;
    private String roleName;
    private String loginIp;
	
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
    
    
}
