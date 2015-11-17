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
import com.campD.portal.common.PageInfo;
import com.campD.portal.service.SpaceService;
import com.campD.portal.util.FileUtils;
import com.campD.portal.util.SystemMessage;

/**
 * 
 * @author qrh
 *
 */
@Controller
@RequestMapping("/space")
public class SpaceController extends BaseController {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private SpaceService spaceService;
	
	/**
	 * 后台场地发布
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add.do")
    @ResponseBody
	public JSONView add(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		Map<?, ?> resultMap = spaceService.add(map);
		 
		return getOperateJSONView(resultMap);
	}
	
	/**
	 * 查询场地列表信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getSpaceInfoList.do")
	public String getSpaceInfoList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		bindParamToAttrbute(request);
		Map<String, Object> map = bindParamToMap(request);
		PageInfo pageInfo = getPageInfo(request);//每页显示多少条 、当前页   两个参数
		Map<?, ?> resultMap = spaceService.getSpaceInfoList(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		
		JSONView jsonview=getSearchJSONView(resultMap);
		
		request.setAttribute("jsonview", jsonview);
		 
		return "space/space_index";
	}
	
	/**
	 * 查询场地列表信息  返回json数据
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getSpaceListByParam.do")
	@ResponseBody
	public JSONView getSpaceListByParam(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		PageInfo pageInfo=getPageInfo(request);
		Map<?, ?> resultMap = spaceService.getSpaceInfoList(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		
		return getSearchJSONView(resultMap);
	}
	
	/**
	 * 查询场地列表信息,跳转到精品场地界面
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getSpaceListByLevel.do")
	public String getSpaceListByLevel(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		bindParamToAttrbute(request);
		
		Map<String, Object> map = bindParamToMap(request);
		
		PageInfo pageInfo=getPageInfo(request);
		
		Map<?, ?> resultMap = spaceService.getSpaceInfoList(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		
		JSONView jsonview=getSearchJSONView(resultMap);
		request.setAttribute("jsonview", jsonview);;
		
		return "space/heigh_level";
	}
	
	/**
	 * 查询场地详情
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getSpaceInfoById.do")
	public String getSpaceInfoById(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = spaceService.getSpaceInfoById(map);
		readDscriptionFromRemoteFile(resultMap);
		
		JSONView jsonview=getSearchJSONView(resultMap);
		request.setAttribute("jsonview", jsonview);
		 
		return "space/space_detail";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void readDscriptionFromRemoteFile(Map spaceMap){
		String description = (String) ((Map)spaceMap.get("spaceInfo")).get("description");
		description = description.replace("\\", "/");
		int index = description.indexOf("attached/html");
		if(null!=description && index>-1){ // 将连接更新为内容
			((Map)spaceMap.get("spaceInfo")).put("description",FileUtils.readRemoteFile(SystemMessage.getString("ossResUrl") + description));
		}
	}
	
}
