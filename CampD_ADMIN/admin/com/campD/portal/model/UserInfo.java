package com.campD.portal.model;

import java.io.Serializable;

/**
 * 用户信息dto
 * 
 * @author Rain
 * 
 */
public class UserInfo implements Serializable{
    
	private static final long serialVersionUID = 2195119891567279998L;

	private String id;
    private String mdn;//手机号
    private String userName;//设计者名称
    private String email;//正式姓名
    private String roleName;//用户角色
    private String loginIp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
    
    
    
}
