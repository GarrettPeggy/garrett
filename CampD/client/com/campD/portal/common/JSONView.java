package com.campD.portal.common;

import java.util.HashMap;

import com.campD.portal.util.JsonHelper;

/**
 * 返回json对象视图 操作类型说明，如果为0表示操作类型，前台会ajax提示，其他为获取数据，页面不做提示
 * 
 * @author Rain
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class JSONView extends HashMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7338192046158634011L;

	public static final String RETURN_SUCCESS_CODE = "200";
    
    public static final String RETURN_FAIL_CODE = "-1";
    
    public static final String MESSAGE_ERROR_DEFAULT = "系统错误!";
    
    public static final String MESSAGE_SUCCESS_DEFAULT = "操作成功!";
    
    /**
     * 存放后台返回到前台的list时的key
     */
    private static final String RETURN_DATA_KEY = "returnValue";
    
    private static final String RETURN_CODE_KEY = "returnCode";
    
    private static final String RETURN_MSG_KEY = "returnMsg";
    
    private static final String RETURN_OPERATE_TYPE_KEY = "returnType";
    
    private static final String RETURN_PAGEINFO_KEY = "pageInfo";
    
    public static final String RETURN_OPERATE_TYPE = "0"; // 操作类型
    
    public static final String RETURN_GET_DATA_TYPE = "1"; // 获取数据类型
    
    /**
     * 设置分页
     */
    public void setPageInfo(PageInfo pageInfo) {
        put(RETURN_PAGEINFO_KEY, pageInfo);
    }
    
    /**
     * 设置成操作类型
     */
    public void setOperateReturnType() {
        setReturnType(RETURN_OPERATE_TYPE);
    }
    
    /**
     * 设置成查询类型
     */
    public void setSearchReturnType() {
        setReturnType(RETURN_GET_DATA_TYPE);
    }
    
    private void setReturnType(String type) {
        put(RETURN_OPERATE_TYPE_KEY, type);
    }
    
    public String getReturnType() {
        return (String)this.get(RETURN_OPERATE_TYPE_KEY);
    }
    
    public void setReturnValue(Object data) {
        put(RETURN_DATA_KEY, data);
    }
    
    public void setSuccess() {
        put(RETURN_CODE_KEY, RETURN_SUCCESS_CODE);
    }
    
    public void setFail() {
        put(RETURN_CODE_KEY, RETURN_FAIL_CODE);
    }
    
    public void setReturnCode(String returnCode) {
        put(RETURN_CODE_KEY, returnCode);
    }
    
    public String getReturnCode() {
        return (String)get(RETURN_CODE_KEY);
    }
    
    public void setReturnErrorMsg() {
        put(RETURN_MSG_KEY, MESSAGE_ERROR_DEFAULT);
    }
    
    public void setReturnSuccMsg() {
        put(RETURN_MSG_KEY, MESSAGE_SUCCESS_DEFAULT);
    }
    
    public void setReturnMsg(String msg) {
        put(RETURN_MSG_KEY, msg);
    }
    
    public String getReturnMsg() {
        return (String)get(RETURN_MSG_KEY);
    }
    
    public void addAttribute(String key, Object value) {
        put(key, value);
        if (key != null && !key.equals("returnCode") && !key.equals("returnMsg")) {
            //默认取数据类型
            this.put("returnType", RETURN_GET_DATA_TYPE);
        }
    }
    
    @Override
    public String toString() {
        return JsonHelper.parseToJson(this);
    }
    
}
