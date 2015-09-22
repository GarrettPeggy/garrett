package com.campD.server.restController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.campD.server.common.JSONView;
import com.campD.server.common.PageInfo;
import com.campD.server.common.SystemConstant;
import com.campD.server.common.UserInfoHolder;
import com.campD.server.model.UserInfo;
import com.campD.server.util.JsonHelper;
import com.campD.server.util.StringUtil;

/**
 * 所有业务相关的Action均继承于此类
 * 
 * @author Rain
 * 
 */
public class BaseRestController {

	protected Logger logger = Logger.getLogger(getClass());
	
	public PageInfo pageInfo;

	protected UserInfo getUserInfo() {
		return UserInfoHolder.get();
	}
	
	public PageInfo getPageInfo(HttpServletRequest request){
		pageInfo = new PageInfo(request);
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	/**
	 * 绑定参数到request Attrbute
	 * 
	 * @param request
	 */
	@SuppressWarnings({ "rawtypes"})
	protected void bindParamToAttrbute(HttpServletRequest request) {
		String requestStr = request.getHeader(SystemConstant.REQUEST_PAREMS_NAME);
		Map requestMap = null;
		try {
			String requestStrPrin = URLDecoder.decode(requestStr, "UTF-8");
			requestMap = JsonHelper.parseToObject(requestStrPrin, Map.class);
		} catch (UnsupportedEncodingException e) {
			logger.info("把请求参数绑定到request出错", e);
		}
		Set keySet = requestMap.keySet();
		Iterator keyIeraotr = keySet.iterator();//先迭代出来 
		while (keyIeraotr.hasNext()) {
			String key = (String) keyIeraotr.next();
			String value = (String) requestMap.get(key);
			request.setAttribute(key,value);
		}
	}

	/**
	 * 绑定参数到Map
	 * 
	 * @param request
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String, Object> bindParamToMap(HttpServletRequest request) {
		
		String requestStr = request.getHeader(SystemConstant.REQUEST_PAREMS_NAME);
		Map requestMap = null;
		try {
			String requestStrPrin = URLDecoder.decode(requestStr, "UTF-8");
			requestMap = JsonHelper.parseToObject(requestStrPrin, Map.class);
		} catch (UnsupportedEncodingException e) {
			logger.info("把请求参数绑定到Map出错", e);
		}
		Set keySet = requestMap.keySet();
		Iterator keyIeraotr = keySet.iterator();//先迭代出来  
		Map<String, Object> map = new HashMap<String, Object>();
		while (keyIeraotr.hasNext()) {
			String key = (String) keyIeraotr.next();
			Object val = (String) requestMap.get(key);
			if(!"randomId".equals(key)){
				if("orderBy".equals(key)){
					if(!StringUtil.isEmpty((String) val)){
						Object orderByList = JsonHelper.parseToObject((String) val, List.class);
						map.put(key, orderByList);
					}
					continue;
				}
				map.put(key, val);
			}
		}
		return map;
	}

	/**
	 * 返回查询list的jsonView
	 * @param result
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected JSONView getSearchJSONView(List list) {
		JSONView jsonView = new JSONView();
		jsonView.setSearchReturnType();
		if(list!=null){
			jsonView.setReturnValue(list);
		}
		// pageinfo
		jsonView.setPageInfo(pageInfo);
		return jsonView;
	}
	/**
	 * 返回查询list的jsonView
	 * 
	 * @param result
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected JSONView getSearchJSONView(Map map) {
		JSONView jsonView = new JSONView();
		jsonView.setSearchReturnType();
		if(map!=null){
			jsonView.putAll(map);
		}
		return jsonView;
	}

	/**
	 * 返回操作的jsonView
	 * 
	 * @param result
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected JSONView getOperateJSONView(Map result) {
		JSONView jsonView = new JSONView();
		jsonView.setOperateReturnType();
		if(result!=null){
			jsonView.putAll(result);
		}
		return jsonView;
	}
	protected String errorCodeDialog(String code,HttpServletRequest request) {
		request.setAttribute(SystemConstant.KEY_DIALOG_ERROR_KEY, code);
		return "common/errorDialog.jsp";
	}
	protected String errorMsgDialog(String msg,HttpServletRequest request) {
		request.setAttribute(SystemConstant.KEY_DIALOG_ERROR_MSG, msg);
		return "common/errorDialog.jsp";
	}
	
	private JSONView errorView(String errorCode,String msg) {
		JSONView view = new JSONView();
		if(StringUtil.isEmpty(errorCode)){
			view.setFail();
		}else{
			view.setReturnCode(errorCode);
		}
		if(!StringUtil.isEmpty(msg)){
			view.setReturnMsg(msg);
		}
		return view;
	}
	
	protected JSONView errorCodeSearchView(String code) {
		JSONView view = errorView(code,null);
		view.setSearchReturnType();
		return view;
	}
	protected JSONView errorCodeOpearteView(String code) {
		JSONView view = errorView(code,null);
		view.setOperateReturnType();
		return view;
	}
	
	protected String errorPage(HttpServletRequest request,String code,String msg) {
		JSONView view = new JSONView();
		view.setReturnCode(code);
		view.setReturnMsg(msg);
		request.setAttribute(SystemConstant.KEY_ERROR_JSONVIEW, view);
		return "common/error500.jsp";
	}
	
	protected String errorPage(HttpServletRequest request,String msg) {
		return errorPage(request,null,msg);
	}
	
	protected boolean isSucc(Map map) {
		if(map==null || map.size()<=0){
			return false;
		}
		String code = (String) map.get(SystemConstant.RETURN_CODE_KEY);
		return SystemConstant.RETURN_SUCC.equals(code);
	}

}
