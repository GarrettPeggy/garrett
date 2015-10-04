package com.campD.server.common;

import java.lang.reflect.Field;
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
	
	
	//=======================ep的session中常量==================================
    public static final String USER_INFO = "USER_INFO";//用户信息Session标示 
    public static String SESSION_KEY_FORM_TOKEN = "formsToken";//form中token的key值,session中value是Map&lt;String,String&gt;
    
    //====================ep的request中错误信息key===============================
	public static String KEY_DIALOG_ERROR_KEY = "errorCodeDialog";//显示错误对话框中错误信息code的key(request 作用域)
	public static String KEY_DIALOG_ERROR_MSG = "errorMsgDialog";//显示错误对话框中错误信息的key(request 作用域)
	public static String KEY_ERROR_JSONVIEW = "errorJsonView";//错误页面显示错误信息的key(request 作用域)
	public static String KEY_FORM_TOKEN_NAME = "formTokenName";//表单存取的token名称
	public static String KEY_FORM_TOKEN_VALUE = "formTokenValue";//表单存取的token值
	
	//====================系统中的用户角色===============================
    public static final String USER_ROLE_COMMON = "普通用户";//Cookie中标示用户名
    public static final String USER_ROLE_ADMIN = "管理员";//Cookie中标示密码
    
  //===========================活动范畴定义的key值========================
  	public static String ENTREPRENEURSHIP= "0";//创业
  	public static String BUSINESS= "1";//商务
  	public static String PLAYING= "2";//玩乐
  	public static String MAKE_FRIENDS= "3";//交友
  	public static Map categoryMap = new HashMap();
  	static{
  		categoryMap.put(ENTREPRENEURSHIP, "创业");
  		categoryMap.put(BUSINESS, "商务");
  		categoryMap.put(PLAYING, "玩乐");
  		categoryMap.put(MAKE_FRIENDS, "交友");
  	}
  	//==========================活动类型定义的值============================
  	public static String COMMON_ACTIVITY= "0";//普通活动
  	public static String HOT_ACTIVITY= "1";//热门活动
  	public static Map actTypeMap = new HashMap();
  	static{
  		actTypeMap.put(COMMON_ACTIVITY, "普通活动");
  		actTypeMap.put(HOT_ACTIVITY, "热门活动");
  	}
  	
  	//=============================活动状态的定义============================
  	public static String ACTIVITY_NOT_PUBLISH="0";//活动未发布
  	public static String ACTIVITY_PUBLISH="1";//活动已发布
  	public static Map actstatusMap = new HashMap();
  	static{
  		actstatusMap.put(ACTIVITY_NOT_PUBLISH, "未发布");
  		actstatusMap.put(ACTIVITY_PUBLISH, "已发布");
  	}
  	
  	//=========================场地类型定义的值=============================
  	public static String HACKERSPACE= "0";//众创空间
  	public static String COFFEE_HOUSE= "1";//咖啡厅
  	public static String COMPANY_MEETING_ROOM= "2";//公司会议室
  	public static String COMMUNITY_SITE= "3";//社区场地
  	public static String COMMERCIAL_PLAZA= "4";//商业广场
  	public static Map spaceTypeMap = new HashMap();
  	static{
  		//场地类型
  		spaceTypeMap.put(HACKERSPACE, "众创空间");
  		spaceTypeMap.put(COFFEE_HOUSE, "咖啡厅");
  		spaceTypeMap.put(COMPANY_MEETING_ROOM, "公司会议室");
  		spaceTypeMap.put(COMMUNITY_SITE, "社区场地");
  		spaceTypeMap.put(COMMERCIAL_PLAZA, "商业广场");
  	}
  	//========================场地级别定义的值=============================
  	public static String COMMON_SPACE= "0";//普通场地
  	public static String FINE_SPACE= "1";//精品场地
  	public static Map spaceLevelMap = new HashMap();
  	static{
  		//场地级别
  		spaceLevelMap.put(COMMON_SPACE, "普通场地");
  		spaceLevelMap.put(FINE_SPACE, "精品场地");
  	}
    
    /**
	 * 返回结果
	 * 数据为空500
	 */
	public final static String RETURN_NULL_ERROR = "500";

	/**
	 * 返回结果
	 * 执行成功201
	 */
	public final static String RETURN_NO_SP = "201";

	/**
	 * 返回结果
	 * 数据异常统一返回502
	 */
	public final static String RETURN_DATA_ERROR = "502";

	/**
	 * 返回结果
	 * 数据重复503
	 */
	public final static String RETURN_DOUBLE_ERROR = "503";

	/**
	 * 返回结果
	 * 数据违反长度约束503
	 */
	public final static String RETURN_MAX_ERROR = "508";
	
	/**
	 * 返回结果
	 * 执行失败499
	 */
	public final static String RETURN_EXE_FAIL = "499";
    
	
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
	
}
