package com.campD.portal.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.common.JSONView;
import com.campD.portal.service.ActivityService;

/**
 * 
 * @author qrh
 *
 */
@Controller
@RequestMapping("/activity")
public class ActivityController extends BaseController {
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private ActivityService activityService;
	
	/**
	 * 添加活动需求
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/add.do")
    @ResponseBody
	public JSONView add(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = activityService.add(map);
		 
		return getSearchJSONView(resultMap);
	}
	
	/**
	 * 活动更新
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/update.do")
    @ResponseBody
	public JSONView update(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = activityService.update(map);
		 
		return getSearchJSONView(resultMap);
	}
	
	/**
	 * 更新活动点击次数
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/updateClick.do")
    @ResponseBody
	public JSONView updateClick(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = activityService.updateClick(map);
		 
		return getSearchJSONView(resultMap);
		
	}
	
	/**
	 * 根据活动所属范畴id,活动提交时间，活动状态等信息查询活动
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getActivityList.do")
    @ResponseBody
	public JSONView getActivityList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = activityService.getActivityList(map);
		 
		return getSearchJSONView(resultMap);
	}
	
	/**
	 * 根据Id查询活动详情
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getActivityById.do")
    @ResponseBody
	public JSONView getActivityById(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = activityService.getActivityById(map);
		 
		return getSearchJSONView(resultMap);
	}
	
	/**
	 * 用户参加活动
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/takeAnActive.do")
    @ResponseBody
	public JSONView takeAnActive(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = activityService.takeAnActive(map);
		 
		return getSearchJSONView(resultMap);
	}
	
	/**
	 * 查询我的报名活动
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getMyTakeAnActive.do")
    @ResponseBody
	public JSONView getMyTakeAnActive(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = activityService.getMyTakeAnActive(map);
		 
		return getSearchJSONView(resultMap);
	}
	
}
