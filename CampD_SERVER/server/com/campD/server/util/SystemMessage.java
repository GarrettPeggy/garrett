package com.campD.server.util;
import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 配置
 * 1.先加载本地配置文�?
 * 2.如果配置�?要从远程读取,那么获取远程文件配置覆盖掉本地配�?
 * @author Rain
 *
 */
public class SystemMessage {

	private final static Logger logger = Logger.getLogger(SystemMessage.class);
	public static Properties properties = new Properties();
	
	//本地配置文件
	private static final String BUNDLE_NAME = "system";

	static{
		//�?启本地读取模�?
		ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
		//properties = new Properties();
		Enumeration<String> e = bundle.getKeys();
		while(e.hasMoreElements()){
			String key = e.nextElement();
			properties.put(key, bundle.getString(key));
		}
		logger.info("----------------------------------------------");
		logger.info("----开启本地读取模式，读取本地配置system--------");
		logger.info("----------------------------------------------");
	}
	
	@SuppressWarnings("rawtypes")
	public static Map getAllConfig(){
		return properties;
	}
	
	public static String getString(String key) {
		return properties.getProperty(key);
	}
	
	public static void main(String[] args) {
		System.out.println(getAllConfig());
	}
	
}

