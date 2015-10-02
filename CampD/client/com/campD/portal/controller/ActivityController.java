package com.campD.portal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.common.JSONView;
import com.campD.portal.common.PageInfo;
import com.campD.portal.model.UserInfo;
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
	@RequestMapping("/add.do")
	@ResponseBody
	public JSONView add(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		UserInfo userInfo = getUserInfo();
		Map<String, Object> map = bindParamToMap(request);
		map.put("creator", userInfo.getId());
		Map<?, ?> resultMap = activityService.add(map);
		
		return getOperateJSONView(resultMap);
		
	}
	
	/**
	 * 跳转到添加活动界面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addUI.do")
	public String addUI(HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		return "activity/activity_hold";
		
	}
	/**
	 * 跳转到添加活动时的添加活动类型界面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityType.do")
	public String getActivityType(HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		return "activity/hold_type";
		
	}
	
	/**
	 * 跳转到添加活动时的选择城市界面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityCity.do")
	public String getActivityCity(HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		return "activity/hold_city";
		
	}
	
	/**
	 * 跳转到添加活动时选择人数范围
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityPeople.do")
	public String getActivityPeople(HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		return "activity/hold_people";
		
	}
	
	/**
	 * 跳转到添加活动时填写活动需求
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityDesc.do")
	public String getActivityDesc(HttpServletResponse response, HttpServletRequest request) throws Exception{
		
		return "activity/hold_desc";
		
	}
	
	/**
	 * 活动更新
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update.do")
    @ResponseBody
	public JSONView update(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		Map<?, ?> resultMap = activityService.update(map);
		 
		return getOperateJSONView(resultMap);
	}
	
	/**
	 * 更新活动点击次数
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateClick.do")
    @ResponseBody
	public JSONView updateClick(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		Map<?, ?> resultMap = activityService.updateClick(map);
		 
		return getOperateJSONView(resultMap);
		
	}
	
	/**
	 * 根据活动所属范畴id,活动提交时间，活动状态等信息查询活动
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityList.do")
	@ResponseBody
	public JSONView getActivityList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		PageInfo pageInfo = getPageInfo(request);
		
		Map<?, ?> resultMap = activityService.getActivityList(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		 
		return getOperateJSONView(resultMap);
	}
	
	/**
	 * 跳转到活动分类页面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityListClassify.do")
	public String getActivityListClassify(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getActivityList(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		JSONView jsonview=getSearchJSONView(resultMap);
		request.setAttribute("jsonview", jsonview);
		 
		return "activity/activity_classify";
	}
	
	/**
	 * 根据活动所属范畴id查询活动，跳转到各种活动的再分类界面,热门活动界面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityListByParam.do")
	public String getActivityListByParam(HttpServletResponse response, HttpServletRequest request) throws Exception {
		bindParamToAttrbute(request);
		Map<String, Object> map = bindParamToMap(request);
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getActivityList(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		JSONView jsonview=getSearchJSONView(resultMap);
		
		request.setAttribute("categoryId", map.get("categoryId"));//活动范畴放到页面上
		request.setAttribute("jsonview", jsonview);
		 
		if((null==map.get("categoryId") || "".equals(map.get("categoryId"))) && ( null!=map.get("actType") && !"".equals(map.get("actType")))){
			return "activity/popular_activity";
		}else{
			return "activity/all_activity_list";
		}
	}
	/**
	 * 查询要主办的活动   根据活动创建者来查询
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityListByUserId.do")
	public String getActivityListByUserId(HttpServletResponse response, HttpServletRequest request) throws Exception {
		bindParamToAttrbute(request);
		UserInfo userInfo= getUserInfo();
		
		Map<String, Object> map = bindParamToMap(request);
		map.put("creatorId", userInfo.getId());
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getActivityList(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		JSONView jsonview=getSearchJSONView(resultMap);
		request.setAttribute("jsonview", jsonview);
		return "activity/sponsored";
		
	}
	
	/**
	 * 根据Id查询活动详情
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityById.do")
	public String getActivityById(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = activityService.getActivityById(map);
		JSONView jsonview=getSearchJSONView(resultMap);
		
		request.setAttribute("jsonview", jsonview);
		 
		return "activity/activity_detail";
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
		
		UserInfo userInfo = getUserInfo();
		Map resultMap = new HashMap();
		Map<String, Object> map = bindParamToMap(request);

		map.put("userId", userInfo.getId());//设置userId
		resultMap = activityService.takeAnActive(map);
		
		//返回json格式数据
		return getOperateJSONView(resultMap);
	}
	
	/**
	 * 查询我的报名活动   跳转到已报名的活动界面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getMyTakeAnActive.do")
	public String getMyTakeAnActive(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		UserInfo userInfo = getUserInfo();

		Map<String, Object> map = bindParamToMap(request);
		map.put("userId", userInfo.getId());
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getMyTakeAnActive(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		JSONView jsonview = getSearchJSONView(resultMap);
		
		request.setAttribute("jsonview", jsonview);
		
		return "activity/sign_up";
	}
	
	/**
	 * 查询我的报名活动   跳转到已报名的活动界面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getMyTakeAnActiveAjax.do")
	@ResponseBody
	public JSONView getMyTakeAnActiveAjax(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		UserInfo userInfo = getUserInfo();

		Map<String, Object> map = bindParamToMap(request);
		map.put("userId", userInfo.getId());
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getMyTakeAnActive(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		
		return getSearchJSONView(resultMap);
	}
	
}
