package com.campD.server.exception;

/**
 * 数据异常
 * @author erping.zhou
 *
 */
@SuppressWarnings({"serial" })
public class DataException extends RuntimeException {
	
	public DataException(String msg) {
		super(msg);
	}
	
	public DataException(Exception e) {
		super(e);
	}
	
	public DataException(String msg,Exception e) {
		super(msg,e);
	}
	
	
	

}
