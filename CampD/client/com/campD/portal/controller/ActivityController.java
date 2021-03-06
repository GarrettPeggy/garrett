package com.campD.portal.controller;

import java.util.HashMap;
import java.util.List;
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
import com.campD.portal.common.SystemConstant;
import com.campD.portal.model.UserInfo;
import com.campD.portal.service.ActivityService;
import com.campD.portal.util.FileUtils;
import com.campD.portal.util.SystemMessage;

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
		map.put("sponsored", 1); //参数sponsored=0表示自己要举办的活动，sponsored=1表示不是自己要举办的活动，好在后台进行按照创建时间排序
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
		 
		return "activity/activity_classify";
	}
	
	/**
	 * 根据活动所属范畴id查询活动，跳转到各种活动的再分类界面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityListByParam.do")
	public String getActivityListByParam(HttpServletResponse response, HttpServletRequest request) throws Exception {
		bindParamToAttrbute(request);
		
		return "activity/all_activity_list";
	}
	
	/**
	 * 根据活动类型查询活动，跳转到热门活动界面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityListByActType.do")
	public String getActivityListByActType(HttpServletResponse response, HttpServletRequest request) throws Exception {
		bindParamToAttrbute(request);
		Map<String, Object> map = bindParamToMap(request);
		map.put("sponsored", 1); //参数sponsored=0表示自己要举办的活动，sponsored=1表示不是自己要举办的活动，好在后台进行按照创建时间排序
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getActivityList(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		JSONView jsonview=getSearchJSONView(resultMap);
		
		request.setAttribute("jsonview", jsonview);
		
		return "activity/popular_activity";
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
		map.put("sponsored", 0); //参数sponsored=0表示自己要举办的活动，sponsored=1表示不是自己要举办的活动，好在后台进行按照创建时间排序
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
		// 从远程需求文件读取活动需求
		readRequirementFromRemoteFile(resultMap);
		
		UserInfo userInfo=(UserInfo) request.getSession().getAttribute(SystemConstant.USER_INFO);
		//如果用户登录，判断该活动是否是该用户的
		if(null!=userInfo){
			String activityId=map.get("id").toString();//活动id
			String creatorId=userInfo.getId();//用户Id
			String userId=userInfo.getId();//用户Id
			map.put("creatorId", creatorId);
			map.put("userId", userId);
			map.put("activityId", activityId);
			//查询该活动是否是该用户的
			Map activityList=activityService.getActivityList(map, pageInfo, true);
			JSONView activityListJsonview=getSearchJSONView(activityList);
			List activityListJsonArray=(List) activityListJsonview.get("activityList");
			logger.info(activityListJsonview);
			
			int flag=0;//标志位，说明该活动是否是该用户的   0不是该用户的  1是该用户的   2该活动不是该用户的但是已经报名
			if(null!=activityListJsonArray && activityListJsonArray.size()>0){
				flag=1;
				logger.info("flag=============="+flag);
			}
			//判断该用户是否已经报名该活动
			Map takeAnActiveList=activityService.getMyTakeAnActive(map, pageInfo, true);
			JSONView takeAnActiveListJsonview=getSearchJSONView(takeAnActiveList);
			List takeAnActiveListJsonArray=(List) takeAnActiveListJsonview.get("activityList");
			
			if(null!=takeAnActiveListJsonArray && takeAnActiveListJsonArray.size()>0){
				flag=2;
				logger.info("flag=============="+flag);
			}
			request.setAttribute("flag", flag);
		}
		
		JSONView jsonview=getSearchJSONView(resultMap);
		request.setAttribute("jsonview", jsonview);
		 
		return "activity/activity_detail";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void readRequirementFromRemoteFile(Map activityMap){
		String requirement = (String) ((Map)activityMap.get("activityInfo")).get("requirement");
		requirement = requirement.replace("\\", "/");
		int index = requirement.indexOf("attached/html");
		if(null!=requirement && index>-1){ // 将连接更新为内容
			((Map)activityMap.get("activityInfo")).put("requirement",FileUtils.readRemoteFile(SystemMessage.getString("ossResUrl") + requirement));
		}
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
		map.put("sponsored", 1); //参数sponsored=0表示自己要举办的活动，sponsored=1表示不是自己要举办的活动，好在后台进行按照创建时间排序
		map.put("userId", userInfo.getId());
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getMyTakeAnActive(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		JSONView jsonview = getSearchJSONView(resultMap);
		
		request.setAttribute("jsonview", jsonview);
		
		return "activity/sign_up";
	}
	
	/**
	 * 查询我的报名活动  
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
		map.put("sponsored", 1); //参数sponsored=0表示自己要举办的活动，sponsored=1表示不是自己要举办的活动，好在后台进行按照创建时间排序
		map.put("userId", userInfo.getId());
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getMyTakeAnActive(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		
		return getSearchJSONView(resultMap);
	}
	
}
