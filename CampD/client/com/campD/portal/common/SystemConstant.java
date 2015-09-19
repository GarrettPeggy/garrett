package com.campD.portal.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * 常量定义
 * 名称要形�?
 * @author Rain
 *
 */
public class SystemConstant {
	
	public static final String YES = "1";
	public static final String NO = "0";
	
	public static final String RETURN_SUCC = "200";//返回成功"200"
    public static final String RETURN_CODE_KEY = "returnCode";//返回�?
	public static final String REQUEST_PAREMS_NAME = "requestMap";
    
  //====================cookie中错误信息key===============================
    public static final String LOGIN_COOKIE_USERNAME_FLAG = "ck_mdn";//Cookie中标示用户名
    public static final String LOGIN_COOKIE_PASSWORD_FLAG = "ck_password";//Cookie中标示密码
	
	
	//=======================session中常量==================================
    public static final String USER_INFO = "USER_INFO";//用户信息Session标示 
    public static String SESSION_KEY_FORM_TOKEN = "formsToken";//form中token的key值,session中value是Map&lt;String,String&gt;
    
    //====================request中错误信息key===============================
	public static String KEY_DIALOG_ERROR_KEY = "errorCodeDialog";//显示错误对话框中错误信息code的key(request 作用域)
	public static String KEY_DIALOG_ERROR_MSG = "errorMsgDialog";//显示错误对话框中错误信息的key(request 作用域)
	public static String KEY_ERROR_JSONVIEW = "errorJsonView";//错误页面显示错误信息的key(request 作用域)
	public static String KEY_FORM_TOKEN_NAME = "formTokenName";//表单存取的token名称
	public static String KEY_FORM_TOKEN_VALUE = "formTokenValue";//表单存取的token值
	
	//===========================常用错误码start=============================
	public static String ERROR_CODE_UNLOGIN = "900";//未登录900
	public static String ERROR_CODE_PARAM_NULL = "901";//前端参数错误 901
	public static String ERROR_CODE_FORM_RESUBMIT = "905";//表单重复提交"905"
	public static String ERROR_CODE_FILE_TOO_BIG = "903";//文件过大错误 903
	public static String ERROR_CODE_FILE_FORMAT = "904";//文件类型错误 904
	
	/**
	 * 将当前静态类的属性转换成Map
	 * @return
	 */
	public static Map<String,Object> toMap(){
		
		Field fs[] = SystemConstant.class.getFields();
		Map<String,Object> map = new HashMap<String,Object>();
		
		for(Field f:fs){
			String key = f.getName();
			try {
				Object o = f.get(key);
				map.put(key, o);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		return map;
	}
	
	public static void main(String[] args){
		
		try {
			String testStr = URLEncoder.encode("王光华","UTF-8");
			System.out.println("编码前->"+testStr);
			String testDeStr = URLDecoder.decode("%C3%A7%C2%8E%C2%8B%C3%A5%C2%85%C2%89%C3%A5%C2%8D%C2%8E%22%7D","UTF-8");
			System.out.println("编码后->"+testDeStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
