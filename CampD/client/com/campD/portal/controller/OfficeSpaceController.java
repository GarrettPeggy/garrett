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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.common.JSONView;
import com.campD.portal.common.PageInfo;
import com.campD.portal.service.GiftService;
import com.campD.portal.service.OfficeService;
import com.campD.portal.service.OfficeSpaceService;
import com.campD.portal.service.SpaceService;

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
	
	@Autowired
	private OfficeService officeService;
	
	@Autowired
	private SpaceService spaceService;
	
	@Autowired
	private GiftService giftService;
	
	/**
	 * 根据名称全文搜索场地、礼品、办公空间
	 */
	@SuppressWarnings({ "unchecked"})
	@RequestMapping("/resourseList.do")
	@ResponseBody
	public JSONView resourseList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		PageInfo pageInfo=getPageInfo(request);
		Map<?, ?> officeListMap = officeService.getList(map,pageInfo);
		Map<?, ?> spaceListMap = spaceService.getSpaceInfoList(map, pageInfo, false);
		Map<?, ?> giftListMap = giftService.getList(map,pageInfo, false);
		
		JSONView resourseListMap = new JSONView();
		resourseListMap.put("giftListMap", giftListMap);
		resourseListMap.put("spaceListMap", spaceListMap);
		resourseListMap.put("officeListMap", officeListMap);
		
		// 此处需要判断三次请求都成功了才真正返回成功
		resourseListMap.setSuccess();
		resourseListMap.setReturnSuccMsg();
		
		return getSearchJSONView(resourseListMap);
	}

	/**
	 * 总空间列表页
	 * @throws Exception
	 */
	@RequestMapping("/toList.do")
	public String toList() throws Exception {
		return "officeSpace/logoList";
	}
	
	/**
	 * 查询空间列表
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public JSONView getList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		PageInfo pageInfo=getPageInfo(request);
		Map<?, ?> officeSpaceListMap = officeSpaceService.getList(map,pageInfo);
		
		return getSearchJSONView(officeSpaceListMap);
	}
	
	/**
	 * 查询详细信息
	 */
	@RequestMapping("/getById.do")
	@ResponseBody
	public JSONView getById(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		Map<?, ?> officeMap = officeSpaceService.getById(map);
		
		return getSearchJSONView(officeMap);
	}
	
}
