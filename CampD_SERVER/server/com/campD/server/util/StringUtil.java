package com.campD.server.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtil 字符串工具类.
 * 
 * @author yunhu.duan
 * @version 1.0, 2014/09/01
 */

public final class StringUtil {

	/**
	 * 判断字符串是否为�?
	 * 
	 * @param stringValue
	 *            参数字符�?
	 * @return true-�? false-非空
	 */
	public static boolean isEmpty(String stringValue) {
		return stringValue == null || stringValue.trim().length() == 0;
	}

	/**
	 * 判断字符串是否为数字类型
	 * 
	 * @param stringValue
	 *            参数字符�?
	 * @return true-非数�? false-数字
	 */
	public static boolean isDigital(String stringValue) {

		if (isEmpty(stringValue)) {
			return false;
		}

		for (int i = 0; i < stringValue.toCharArray().length; i++) {
			char c = stringValue.toCharArray()[i];
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * 取得指定字符串的长度<br>
	 * �?个英文字母或数字的长度为1，一个中文汉字的长度�?2<br>
	 * 如果字符串为空，返回长度0
	 * 
	 * @param str
	 *            指定的字符串
	 * @return
	 */
	public static int getLength(String str) {
		if (isEmpty(str)) {
			return 0;
		}
		return str.getBytes().length;
	}

	/**
	 * 字符串转化为整型
	 * 
	 * @param stringNumber
	 *            要转化的字符�?
	 * @return 转化后的整型�?
	 */
	public static int string2Int(String stringNumber) {
		if (isEmpty(stringNumber) || !isDigital(stringNumber)) {
			throw new IllegalArgumentException(
					"String number parameter is null or not digital, invalid value :"
							+ stringNumber);
		}
		int intValue = 0;
		try {
			double doubleValue = string2Double(stringNumber);
			if (doubleValue >= Integer.MAX_VALUE) {
				throw new IllegalArgumentException("Too large Int value!!!");
			}
			intValue = Integer.parseInt(stringNumber);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return intValue;
	}

	/**
	 * 字符串转化为Double
	 * 
	 * @param pDoubleValue
	 *            要转化的字符�?
	 * @return 转化后的Double
	 */
	public static double string2Double(String pDoubleValue) {
		if (isEmpty(pDoubleValue) || !isDigital(pDoubleValue)) {
			throw new IllegalArgumentException(
					"String parameter is null or not digital, invalid value :"
							+ pDoubleValue);
		}
		double doubleValue = 0d;
		try {
			doubleValue = Double.valueOf(pDoubleValue).doubleValue();
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"String parameter is not number!!!");
		}
		return doubleValue;
	}

	/**
	 * 字符串转化为Long
	 * 
	 * @param pLongValue
	 *            要转化的字符�?
	 * @return 转化后的Long
	 */
	public static long string2Long(String pLongValue) {
		if (isEmpty(pLongValue) || !isDigital(pLongValue)) {
			throw new IllegalArgumentException(
					"String parameter is null or not digital, invalid value :"
							+ pLongValue);
		}
		long longValue = 0;
		try {
			longValue = Long.valueOf(pLongValue).longValue();
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"String parameter is not number!!!");
		}
		return longValue;
	}

	/**
	 * �? null 的字符串对象转换成空字符�?
	 * 
	 * @param nullString
	 *            null 的字符串对象
	 * @return
	 */
	public static String nullToEmpty(String nullString) {
		if (isEmpty(nullString)) {
			return "";
		}
		return nullString;
	}

	/**
	 * �? null 的字符串对象转换�?0
	 * 
	 * @param nullString
	 *            null 的字符串对象
	 * @return
	 */
	public static String nullToZero(Object nullObj) {
		return nullObj == null ? "0" : String.valueOf(nullObj);
	}

	public static String replaceAllCharactors(String str, String[] arrRegStr,
			String newStr) {
		if (str == null) {
			return "";
		}
		if (arrRegStr == null || arrRegStr.length < 1) {
			return "";
		}
		if (newStr == null || "".equals(newStr)) {
			newStr = ",";
		}
		for (String each : arrRegStr) {
			str = str.replaceAll(each, newStr);
		}
		return str;
	}

	/**
	 * 获取字符串中存在指定字符串数�?
	 * 
	 * @param sourceStr
	 *            原字符串
	 * @param specifyStr
	 *            指定字符
	 * @return
	 */
	public static int getSpecifyStrCountInStr(String sourceStr,
			String specifyStr) {
		int count = 0;
		int fromIndex = 0;
		while (sourceStr.indexOf(specifyStr, fromIndex) != -1) {
			int index = sourceStr.indexOf(specifyStr, fromIndex);
			fromIndex = index + specifyStr.length();
			count++;
		}
		return count;
	}
	
	public static int getCategoryOfChar(String str){
		int level=0;
        Pattern p1 = Pattern.compile("[a-zA-Z]{1,}");//有字�?
    	Pattern p2 = Pattern.compile("[0-9]{1,}");//有数�?
    	Pattern p3 = Pattern.compile("[\\W]{1,}");//有特殊字�?
    
		Matcher matcher1 = p1.matcher(str);
		Matcher matcher2= p2.matcher(str);
		Matcher matcher3 = p3.matcher(str);
		if(matcher1.find()){//包含字母
			level++;
		}
		if(matcher2.find()){//包含数字
			level++;
		}
		if(matcher3.find()){//包含特殊字符
			level++;
		}
		return level;
	}
	private static int getCharNum(Matcher m){
		int c = 0;
    	while(m.find()){
    		c++;
    	}
    	return c;
	}
	/**
	 * 得到密码强度等级
	 * @param passWord 密码
	 * @return 0:�?�? 1:中等  2:复杂
	 */
	public static int getPassWordSafeLevel(String passWord){
		//密码强度
        int level=0;
        Pattern p1 = Pattern.compile("[a-zA-Z]");//有字�?
    	Pattern p2 = Pattern.compile("[0-9]");//有数�?
    	Pattern p3 = Pattern.compile("[\\W]");//有特殊字�?
    	Pattern p4 = Pattern.compile("([0-9a-zA-Z\\W])\\1+");//重复字母或数字或特殊字符
    
		Matcher matcher1 = p1.matcher(passWord);
		Matcher matcher2= p2.matcher(passWord);
		Matcher matcher3 = p3.matcher(passWord);
		Matcher matcher4 = p4.matcher(passWord);
    	boolean isSeq = isSequenceStr(passWord);
    	int category = getCategoryOfChar(passWord);
    	if(matcher4.matches()||isSeq || category<=1){
			//密码强度�?
    		return 0;
		}
    	
    	
    	if(getCharNum(matcher1)>=2){//包含字母
			level++;
		}
		if(getCharNum(matcher2)>=2){//包含数字
			level++;
		}
		if(getCharNum(matcher3)>=2){//包含特殊字符
			level++;
		}
		
		if(category>1 && category<3 && level<=1){
            return 0;
        }
    	if(category>1 && category<3 && level>=2){
    		return 1;//中等密码
    	}else if(category>=3){
    		return 2;//强密�?
    	}
    	return 0;
	}

	public static boolean isSequenceStr(String str) {
		//连号
		boolean isSeq=true;
		int pre=0;
		for(int i=0;i<str.length();i++){
			char d=str.charAt(i);
			if(i==0){
    			pre=d;
    			continue;
    		}
			if(d-1!=pre&&d+1!=pre){
				isSeq=false;
    		}
    		pre = d;
		}
		return isSeq;
	}
	
	/**
     * 隐藏手机号中间四�? //135****4647
     * @param mdn
     * @return 
     */
	public static String getSafeMdn(String mdn){
	    
        if(!StringUtil.isEmpty(mdn) && MdnPatternUtil.validateMdn(mdn)!=-1){
            
            int enda = 3;
            int startb = 7;
            if(mdn.startsWith("86")){
                enda=enda+2;
                startb=startb+2;
            }
            if(mdn.startsWith("+86")){
                enda=enda+3;
                startb=startb+3;
            }
            StringBuffer newMdn = new StringBuffer(mdn.substring(0, enda));
            newMdn.append("****");
            newMdn.append(mdn.substring(startb));
            return newMdn.toString();
        }
        return mdn;
    }


	public static void main(String[] args) {
		String str = "d1f4#f";
		System.out.println(getPassWordSafeLevel(str));
	}
}