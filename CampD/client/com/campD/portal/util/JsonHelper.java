package com.campD.portal.util;
import org.codehaus.jackson.map.ObjectMapper;

import com.campD.portal.exception.DataException;

/**
 * json对象辅助�?
 * @author Rain
 *
 */
public class JsonHelper {
	/**
	 * 据说json==>对象是线程安全的
	 */
	public static ObjectMapper readMapper = new ObjectMapper();
	
	public static <T> T parseToObject(String json,Class<T> toClass){
		try {
			return (T)readMapper.readValue(json, toClass);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException(e);
		}
	}
	
	public static String parseToJson(Object o){
		if(o==null){
			return null;
		}
		ObjectMapper writerMapper = new ObjectMapper();
		try {
			return writerMapper.writeValueAsString(o);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataException(e);
		}
	}
	
	
}
