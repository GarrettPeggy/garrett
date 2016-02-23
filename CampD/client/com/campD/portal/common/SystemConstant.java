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
	public static String ERROR_CODE_USER_NOT_REGISTER = "906";//文件类型错误 904
	
	//===========================活动范畴定义的key值========================
	
	public static String ACTIVITY_CATEGORY_0= "0";
	public static String ACTIVITY_CATEGORY_1= "1";
	public static String ACTIVITY_CATEGORY_2= "4";
	public static String ACTIVITY_CATEGORY_3= "3";
	public static String ACTIVITY_CATEGORY_4= "9";
	public static Map categoryMap = new HashMap();
	static{
		categoryMap.put(ACTIVITY_CATEGORY_0, "创业");
		categoryMap.put(ACTIVITY_CATEGORY_1, "商务");
		categoryMap.put(ACTIVITY_CATEGORY_2, "科技");
		categoryMap.put(ACTIVITY_CATEGORY_3, "社交");
		categoryMap.put(ACTIVITY_CATEGORY_4, "文化");
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
	public static String SPACE_TYPE_0= "0";
	public static String SPACE_TYPE_1= "1";
	public static String SPACE_TYPE_2= "2";
	public static String SPACE_TYPE_3= "3";
	public static String SPACE_TYPE_4= "4";
	public static String SPACE_TYPE_5= "5";
	public static String SPACE_TYPE_6= "6";
	public static String SPACE_TYPE_7= "7";
	public static String SPACE_TYPE_8= "8";
	public static String SPACE_TYPE_9= "9";
	public static String SPACE_TYPE_10= "10";
	public static String SPACE_TYPE_11= "11";
	public static String SPACE_TYPE_12= "12";
	public static String SPACE_TYPE_13= "13";
	public static String SPACE_TYPE_14= "14";
	public static String SPACE_TYPE_15= "15";
	public static String SPACE_TYPE_16= "16";
	public static String SPACE_TYPE_17= "17";
	public static String SPACE_TYPE_18= "18";
	public static String SPACE_TYPE_19= "19";
	public static String SPACE_TYPE_20= "20";
	public static String SPACE_TYPE_21= "21";
	public static String SPACE_TYPE_22= "22";
	public static String SPACE_TYPE_23= "23";
	public static String SPACE_TYPE_24= "24";
	public static String SPACE_TYPE_25= "25";
	public static String SPACE_TYPE_26= "26";
	public static String SPACE_TYPE_27= "27";
	public static Map spaceTypeMap = new HashMap();
	static{
		//场地类型
		spaceTypeMap.put(SPACE_TYPE_0, "餐厅");
		spaceTypeMap.put(SPACE_TYPE_1, "酒楼公园");
		spaceTypeMap.put(SPACE_TYPE_2, "游乐园");
		spaceTypeMap.put(SPACE_TYPE_3, "艺术中心");
		spaceTypeMap.put(SPACE_TYPE_4, "画廊");
		spaceTypeMap.put(SPACE_TYPE_5, "会所");
		spaceTypeMap.put(SPACE_TYPE_6, "俱乐部");
		spaceTypeMap.put(SPACE_TYPE_7, "展览馆");
		spaceTypeMap.put(SPACE_TYPE_8, "秀场");
		spaceTypeMap.put(SPACE_TYPE_9, "会展");
		spaceTypeMap.put(SPACE_TYPE_10, "会议中心");
		spaceTypeMap.put(SPACE_TYPE_11, "学校");
		spaceTypeMap.put(SPACE_TYPE_12, "培训机构");
		spaceTypeMap.put(SPACE_TYPE_13, "度假村");
		spaceTypeMap.put(SPACE_TYPE_14, "农家乐");
		spaceTypeMap.put(SPACE_TYPE_15, "商圈");
		spaceTypeMap.put(SPACE_TYPE_16, "商场");
		spaceTypeMap.put(SPACE_TYPE_17, "剧院");
		spaceTypeMap.put(SPACE_TYPE_18, "礼堂");
		spaceTypeMap.put(SPACE_TYPE_19, "酒吧");
		spaceTypeMap.put(SPACE_TYPE_20, "KTV");
		spaceTypeMap.put(SPACE_TYPE_21, "咖啡厅");
		spaceTypeMap.put(SPACE_TYPE_22, "茶馆");
		spaceTypeMap.put(SPACE_TYPE_23, "体育场馆");
		spaceTypeMap.put(SPACE_TYPE_24, "小区社区");
		spaceTypeMap.put(SPACE_TYPE_25, "写字楼");
		spaceTypeMap.put(SPACE_TYPE_26, "特色场地");
		spaceTypeMap.put(SPACE_TYPE_27, "众创空间");
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
	
	//===========================礼品的形态========================
	public static String GIFT_FORM_0= "0";
	public static String GIFT_FORM_1= "1";
	public static String GIFT_FORM_2= "2";
	public static String GIFT_FORM_3= "3";
	public static Map formMap = new HashMap();
	static{
		formMap.put(GIFT_FORM_0, "实物");
		formMap.put(GIFT_FORM_1, "礼券");
		formMap.put(GIFT_FORM_2, "兑换码");
		formMap.put(GIFT_FORM_3, "红包");
	}
	
	//===========================礼品的主营业务=================注册，法律，财务，服务器，知识产权，创投，软件外包，场地，媒体，营推，管理咨询，人力资源=======
	public static String GIFT_MAIN_BUSSINESS_0= "0";
	public static String GIFT_MAIN_BUSSINESS_1= "1";
	public static String GIFT_MAIN_BUSSINESS_2= "2";
	public static String GIFT_MAIN_BUSSINESS_3= "3";
	public static String GIFT_MAIN_BUSSINESS_4= "4";
	public static String GIFT_MAIN_BUSSINESS_5= "5";
	public static String GIFT_MAIN_BUSSINESS_6= "6";
	public static String GIFT_MAIN_BUSSINESS_7= "7";
	public static String GIFT_MAIN_BUSSINESS_8= "8";
	public static String GIFT_MAIN_BUSSINESS_9= "9";
	public static String GIFT_MAIN_BUSSINESS_10= "10";
	public static String GIFT_MAIN_BUSSINESS_11= "11";
	public static Map mainBusinessMap = new HashMap();
	static{
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_0, "注册");
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_1, "法律");
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_2, "财务");
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_3, "服务器");
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_4, "知识产权");
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_5, "创投");
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_6, "软件外包");
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_7, "场地");
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_8, "媒体");
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_9, "营推");
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_10, "管理咨询");
		mainBusinessMap.put(GIFT_MAIN_BUSSINESS_11, "人力资源");
	}
	
	//========================礼品级别=============================
	public static String GIFT_LEVEL_0= "0";//普通礼品
	public static String GIFT_LEVEL_1= "1";//精品礼品
	public static Map giftLevelMap = new HashMap();
	static{
		//场地级别
		giftLevelMap.put(GIFT_LEVEL_0, "普通礼品");
		giftLevelMap.put(GIFT_LEVEL_1, "精品礼品");
	}
	
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
