package com.campD.portal.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

public class DateUtil {
	private static final String defaultFormat = "yyyy-MM-dd HH:mm:ss";
	
	private static final SimpleDateFormat MONTH_SDF = new SimpleDateFormat("yyyy-MM");
	
	private static final SimpleDateFormat DAY_SDF = new SimpleDateFormat("yyyy-MM-dd");

	public static Date getDate(String text, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;

		try {
			date = sdf.parse(text);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;

	}

	public static Date getDate(String text) {
		return getDate(text, defaultFormat);
	}

	public static String fmtDate(Date date) {
		return fmtDate(date, defaultFormat);
	}

	public static String fmtDate(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		return sdf.format(date);

	}

	public static java.sql.Date getSQLDate(String text, String format) {
		if (text == null || "".equals(text)) {
			return null;
		}

		long time = getDate(text, format).getTime();
		return new java.sql.Date(time);

	}

	public static java.sql.Timestamp getTimestamp(Date date) {
		return new java.sql.Timestamp(date.getTime());
	}

	/**
	 * 获取当月第一天'yyyy-MM' 空或不支持格式返回当前月第一天
	 * 
	 * @param date
	 *            yyyy-MM
	 * @return 'yyyy-MM-dd'
	 */
	public static String getMonthFirstDay(String date) {
		
		Date theDate;
		try {
			theDate = MONTH_SDF.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			theDate = new Date();
		}
		return getMonthFirstDay(theDate);

	}
	
	/**
	 * 获取当月第一天'yyyy-MM' 空或不支持格式返回当前月第一天
	 * @param date
	 * @return 'yyyy-MM-dd'
	 */
	public static String getMonthFirstDay(Date date) {
		return DAY_SDF.format(getFirstDayDate(date));
	}
	
	/**
	 * 获取当月第一天'yyyy-MM' 空或不支持格式返回当前月第一天
	 * @param date
	 * @return 'yyyy-MM-dd'
	 */
	public static Date getFirstDayDate(Date date) {
		GregorianCalendar c = (GregorianCalendar) Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();

	}

	/**
	 * 获取当月最后一天'yyyy-MM' 空或不支持格式返回当前月最后一天
	 * 
	 * @param date
	 * @return 'yyyy-MM-dd'
	 */
	public static String getMonthLastDay(String date) {
		Date theDate;
		try {
			theDate = MONTH_SDF.parse(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
			theDate = new Date();
		}
		return getMonthLastDay(theDate);
	}
	
	/**
	 * 获取当月最后一天'yyyy-MM' 空或不支持格式返回当前月最后一天
	 * 
	 * @param date
	 * @return 'yyyy-MM-dd'
	 */
	public static String getMonthLastDay(Date date) {
		return DAY_SDF.format(getLastDayDate(date));
	}
	/**
	 * 得到最后一天的日期
	 * @param date
	 * @return
	 */
	public static Date getLastDayDate(Date date) {
		GregorianCalendar c = (GregorianCalendar) Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.roll(Calendar.DAY_OF_MONTH, -1);
		return c.getTime();
	}

	/**
	 * 日期添加相应天数
	 * @param date
	 * @return 'yyyy-MM-dd'
	 */
	public static Date addDay(Date date,int increaseNum) {
		GregorianCalendar c = (GregorianCalendar) Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, increaseNum);
		return c.getTime();
	}
	
	/**
	 * 日期添加相应天数
	 * @param date
	 * @return 'yyyy-MM-dd'
	 */
	public static Date addMonth(Date date,int increaseNum) {
		GregorianCalendar c = (GregorianCalendar) Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, increaseNum);
		return c.getTime();
	}
	
	/**
	 * 得到一周中星期几的日期
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDayOfWeek(Date date,int day) {
		GregorianCalendar c = (GregorianCalendar) Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, day);
		return c.getTime();
	}
	
	/**
	 * 把yyyy-MM-dd类型的日期字符串转换成yyyy-MM-dd 00:00:00或者yyyy-MM-dd 23:59:59类型字符串
	 * @param reqMap 请求的map其中包含beginTime,endTime此类字段
	 * @param startTimeKey 开始时间存在reqMap中的key值
	 * @param endTimeKey 结束时间存在reqMap中的key值
	 * @return
	 */
	public static Map<String, Object> setDayToTime(Map<String, Object> reqMap,String startTimeKey,String endTimeKey){
		if(!StringUtil.isEmpty(startTimeKey) && !StringUtil.isEmpty((String)reqMap.get(startTimeKey))){
			String startDate = (String) reqMap.get(startTimeKey);
			startDate = fmtDate(getDate(startDate, "yyyy-MM-dd"),"yyyy-MM-dd");
			reqMap.put(startTimeKey, startDate+" 00:00:00");
		}
		if(!StringUtil.isEmpty(endTimeKey) && !StringUtil.isEmpty((String)reqMap.get(endTimeKey))){
			String endDate = (String) reqMap.get(endTimeKey);
			endDate = fmtDate(getDate(endDate, "yyyy-MM-dd"),"yyyy-MM-dd");
			reqMap.put(endTimeKey, endDate+" 23:59:59");
		}
		return reqMap;
	}
	
	/**
	 * 把yyyy-MM-dd类型的日期字符串转换成yyyy-MM-dd 00:00:00或者yyyy-MM-dd 23:59:59类型字符串
	 * @param reqMap 请求的map其中包含beginTime,endTime此类字段
	 * @param startTimeKey 开始时间存在reqMap中的key值
	 * @param endTimeKey 结束时间存在reqMap中的key值
	 * @return
	 */
	public static Map<String, Object> setDayToTimeWithDefault(Map<String, Object> reqMap,String startTimeKey,String endTimeKey){
		String startTime = (String) reqMap.get(startTimeKey);
		String endTime = (String) reqMap.get(endTimeKey);
		if(StringUtil.isEmpty(startTime)){
			reqMap.put(startTimeKey, getMonthFirstDay(new Date()));
		}
		if(StringUtil.isEmpty(endTime)){
			reqMap.put(endTimeKey, getMonthLastDay(new Date()));
		}
		return setDayToTime(reqMap, startTimeKey, endTimeKey);
	}
	
	public static void main(String[] args) {
		Date d1 = getDate("2014-10-31", "yyyy-MM-dd");
		Date d = addMonth(d1, 1);
		System.out.println(fmtDate(d, "yyyy-MM-dd"));
	}
	
}
