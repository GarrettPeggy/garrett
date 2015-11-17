package com.campD.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.common.JSONView;
import com.campD.portal.common.PageInfo;
import com.campD.portal.common.SystemConstant;
import com.campD.portal.model.UserInfo;
import com.campD.portal.service.ActivityService;
import com.campD.portal.util.FileUtils;
import com.campD.portal.util.OSSUtil;
import com.campD.portal.util.SystemMessage;
import com.campD.portal.util.UploadFileUtil;

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
		
		Map<?, ?> resultMap = null;
		String requirements = (String) map.get("requirement");
		resultMap = uploadRequirementsToOSS(requirements, request);
		
		// 假如需求保存文件成功则将活动保存到数据库
		if(JSONView.RETURN_SUCCESS_CODE.equals(resultMap.get("returnCode"))){
			map.put("actType", Integer.parseInt(SystemConstant.COMMON_ACTIVITY));//活动类型   0:普通活动   1:热门活动
			map.put("creator", userInfo.getId());
			map.put("requirement", resultMap.get("requirementsURL"));
			resultMap = activityService.add(map);
		}
		
		return getOperateJSONView(resultMap);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map uploadRequirementsToOSS(String requirements, HttpServletRequest request){
		
		Map returnMap = null;
		String fileName = FileUtils.getNumberFileName(UUID.randomUUID().toString() + "_" + new Random().nextInt(1000));// 随机生成一个文件名
		String tmpPath = UploadFileUtil.getTmpFileRealPath(fileName, getUserInfo(), request);//文件保存目录路径
		String relativePath = UploadFileUtil.getRelativePath(tmpPath, request).replace("\\", "/");// 相对路径
		FileUtils.writerFile(requirements, tmpPath);
		
		// 往阿里OOS上面上传文件
		returnMap = OSSUtil.uploadFile(tmpPath);
		returnMap.put("requirementsURL", relativePath);
		
		return returnMap;
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
		
		return "activity/addActivity";
		
	}
	
	/**
	 * 跳转到修改活动界面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toEditActivity.do")
	public String toEditActivity(HttpServletResponse response, ModelMap mop, HttpServletRequest request) throws Exception{
		
		Map reqMap = bindParamToMap(request);
		Map activityMap = activityService.getActivityById(reqMap);
		// 读取远程活动需求的内容
		readRequirementFromRemoteFile(activityMap);
		mop.addAttribute("activityMap", activityMap);
		
		bindParamToAttrbute(request);
		
		return "activity/editActivity";
		
	}
	
	/**
	 * 跳转到查看场地信息界面
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/toViewActivity.do")
	public String toViewActivity(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
		
		Map reqMap = bindParamToMap(request);
		Map activityMap = activityService.getActivityById(reqMap);
		// 读取远程活动需求的内容
		readRequirementFromRemoteFile(activityMap);
		mop.addAttribute("activityMap", activityMap);
		
		bindParamToAttrbute(request);
		
		return "activity/viewActivity"; 
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void readRequirementFromRemoteFile(Map activityMap){
		String requirement = (String) ((Map)activityMap.get("activityInfo")).get("requirement");
		requirement = requirement.replace("\\", "/");
		int index = requirement.indexOf("attached/html");
		if(null!=requirement && index>-1){ // 将连接更新为内容
			((Map)activityMap.get("activityInfo")).put("originalRequirementURL", requirement.substring(index));
			((Map)activityMap.get("activityInfo")).put("requirement",FileUtils.readRemoteFile(SystemMessage.getString("ossResUrl") + requirement));
		}
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

		Map<?, ?> resultMap = null;
		String requirements = (String) map.get("requirement");
		// 重新生成活动需求的文件
		resultMap = uploadRequirementsToOSS(requirements, request);
		// 删除原有的文件
		if(null!=map.get("originalRequirementURL")){
			OSSUtil.deleteFile((String) map.get("originalRequirementURL"));
		}
		
		// 假如需求保存文件成功则将活动保存到数据库
		if(JSONView.RETURN_SUCCESS_CODE.equals(resultMap.get("returnCode"))){
			map.put("requirement", resultMap.get("requirementsURL"));
			resultMap = activityService.update(map);
		}
		 
		return getOperateJSONView(resultMap);
	}
	
	/**
	 * 更新活动类型
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateActType.do")
    @ResponseBody
	public JSONView updateActType(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map resultMap = activityService.updateActType(map);
		 
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
	 * 活动列表查询
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getActivityList.do")
	public String getActivityList(HttpServletResponse response, ModelMap mop, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		PageInfo pageInfo = getPageInfo(request);
		
		Map<?, ?> resultMap = activityService.getActivityList(map,pageInfo);
		
		mop.addAttribute("resultMap", resultMap);
		 
		return "activity/activityListCtx";
	}
	
	/**
	 * 跳转到活动列表页面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toActivityList.do")
	public String toActivityList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		return "activity/activityList";
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
		Map<?, ?> resultMap = activityService.getActivityList(map,pageInfo);
		JSONView jsonview=getSearchJSONView(resultMap);
		request.setAttribute("jsonview", jsonview);
		 
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
		Map<String, Object> map = bindParamToMap(request);
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getActivityList(map,pageInfo);
		JSONView jsonview=getSearchJSONView(resultMap);
		
		request.setAttribute("categoryId", map.get("categoryId"));//活动范畴放到页面上
		request.setAttribute("jsonview", jsonview);
		
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
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getActivityList(map,pageInfo);
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
		map.put("creatorId", userInfo.getId());
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getActivityList(map,pageInfo);
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
			Map activityList=activityService.getActivityList(map, pageInfo);
			
			JSONView activityListJsonview=getSearchJSONView(activityList);
			
			List activityListJsonArray=(List) activityListJsonview.get("activityList");
			
			System.out.println(activityListJsonview);
			
			int flag=0;//标志位，说明该活动是否是该用户的   0不是该用户的  1是该用户的   2该活动不是该用户的但是已经报名
			if(null!=activityListJsonArray && activityListJsonArray.size()>0){
				flag=1;
				logger.info("flag=============="+flag);
			}
			//判断该用户是否已经报名该活动
			Map takeAnActiveList=activityService.getMyTakeAnActive(map, pageInfo);
			
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
		Map<?, ?> resultMap = activityService.getMyTakeAnActive(map,pageInfo);
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
		map.put("userId", userInfo.getId());
		PageInfo pageInfo = getPageInfo(request);
		Map<?, ?> resultMap = activityService.getMyTakeAnActive(map,pageInfo);
		
		return getSearchJSONView(resultMap);
	}
	
}
