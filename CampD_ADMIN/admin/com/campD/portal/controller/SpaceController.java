/**
 * 
 */
package com.campD.portal.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.common.PageInfo;
import com.campD.portal.model.UserInfo;
import com.campD.portal.service.SpaceService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/space")
public class SpaceController extends BaseController {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private SpaceService spaceService;
	
	@RequestMapping("/toList.do")
    public String toList(){
		
        return "space/spaceList";
    }
	
	/**
	 * 场地列表--查询
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/list.do")
	public String querySpaceList(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
		
		Map reqMap = bindParamToMap(request);
		PageInfo pageInfo = getPageInfo(request);
		Map spaceListMap = spaceService.getSpaceList(reqMap, pageInfo);
		mop.addAttribute("spaceListMap", spaceListMap);
		
		return "space/spaceListCtx"; 
	}
	
	/**
	 * @return
	 * 去添加场地信息
	 */
	@RequestMapping("/toAdd.do")
    public String toAdd(){
		
        return "space/addSpaceInfo";
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/add.do")
    @ResponseBody
    public Map addSpaceInfo(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		UserInfo userInfo = getUserInfo();
		reqMap.put("creatorId", userInfo.getId());
		Map returnMap = spaceService.addSpace(reqMap);
		
		return getOperateJSONView(returnMap);
    }
	
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/update.do")
    @ResponseBody
    public Map updateSpaceInfo(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		Map returnMap = spaceService.updateSpace(reqMap);
		
		return getOperateJSONView(returnMap);
    }
	
	/**
	 * 去编辑场地信息
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/toEditSpace.do")
	public String toEditSpace(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
		
		Map reqMap = bindParamToMap(request);
		Map spaceMap = spaceService.getSpaceById(reqMap);
		mop.addAttribute("spaceMap", spaceMap);
		
		bindParamToAttrbute(request);
		return "space/editSpaceInfo"; 
	}
	
	/**
	 * 去预览场地信息
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/toViewSpace.do")
	public String toViewSpace(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
		
		Map reqMap = bindParamToMap(request);
		Map spaceMap = spaceService.getSpaceById(reqMap);
		mop.addAttribute("spaceMap", spaceMap);
		
		return "space/viewSpaceInfo"; 
	}
}
