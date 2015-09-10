package com.campD.portal.controller;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.campD.portal.common.JSONView;
import com.campD.portal.common.PageInfo;
import com.campD.portal.common.SystemConstant;
import com.campD.portal.common.UserInfoHolder;
import com.campD.portal.model.UserInfo;
import com.campD.portal.util.JsonHelper;
import com.campD.portal.util.StringUtil;

/**
 * 所有业务相关的Action均继承于此类
 * 
 * @author Rain
 * 
 */
public class BaseController {

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
	protected void bindParamToAttrbute(HttpServletRequest request) {
		Enumeration enumer = request.getParameterNames();
		while (enumer.hasMoreElements()) {
			String key = (String) enumer.nextElement();
			String[] values = request.getParameterValues(key);
			if(values.length>1){
				request.setAttribute(key,values);
			}else{
				request.setAttribute(key, request.getParameter(key));
			}
		}
	}

	/**
	 * 绑定参数到Map
	 * 
	 * @param request
	 */
	protected Map<String, Object> bindParamToMap(HttpServletRequest request) {
		Enumeration enumer = request.getParameterNames();
		Map<String, Object> map = new HashMap<String, Object>();
		while (enumer.hasMoreElements()) {
			String key = (String) enumer.nextElement();
			String val = request.getParameter(key);// 在此需要将值进行解码称utf-8
			try {
				val = new String(val.getBytes("iso8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!"randomId".equals(key)){
				if("orderBy".equals(key)){
					if(!StringUtil.isEmpty(val)){
						Object orderByList = JsonHelper.parseToObject(val, List.class);
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
