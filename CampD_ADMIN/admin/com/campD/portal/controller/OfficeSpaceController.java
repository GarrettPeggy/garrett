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
import com.campD.portal.service.OfficeSpaceService;

/**
 * @author Garrett
 *
 */
@Controller
@RequestMapping("/officeSpace")
public class OfficeSpaceController extends BaseController {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private OfficeSpaceService officeSpaceService;
	
	@RequestMapping("/toList.do")
    public String toList(){
		
        return "officeSpace/officeSpaceList";
    }
	
	/**
	 * 空间列表--查询
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/list.do")
	public String queryOfficeSpaceList(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
		
		Map reqMap = bindParamToMap(request);
		PageInfo pageInfo = getPageInfo(request);
		Map officeSpaceListMap = officeSpaceService.getList(reqMap, pageInfo);
		mop.addAttribute("officeSpaceListMap", officeSpaceListMap);
		
		return "officeSpace/officeSpaceListCtx"; 
	}
	
	/**
	 * @return
	 * 去添加空间信息
	 */
	@RequestMapping("/toAdd.do")
    public String toAdd(){
		
        return "officeSpace/addOfficeSpace";
    }
	
	/**
	 * 添加空间信息
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/add.do")
    @ResponseBody
    public Map add(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		Map<?, ?> returnMap = officeSpaceService.add(reqMap);
		
		return getOperateJSONView(returnMap);
    }
	
	/**
	 * 去编辑空间信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/toEdit.do")
	public String toEdit(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
		
		Map reqMap = bindParamToMap(request);
		Map officeSpaceMap = officeSpaceService.getById(reqMap);
		mop.addAttribute("officeSpaceMap", officeSpaceMap);
		
		bindParamToAttrbute(request);
		return "officeSpace/editOfficeSpace"; 
	}
	
	/**
	 * 更新空间信息
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/update.do")
    @ResponseBody
    public Map update(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		Map<?, ?> returnMap = officeSpaceService.update(reqMap);
		
		return getOperateJSONView(returnMap);
    }
	
	/**
	 * 去预览场地信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/toView.do")
	public String toView(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
		
		Map reqMap = bindParamToMap(request);
		Map officeSpaceMap = officeSpaceService.getById(reqMap);
		mop.addAttribute("officeSpaceMap", officeSpaceMap);
		
		return "officeSpace/viewOfficeSpace"; 
	}
	
}
