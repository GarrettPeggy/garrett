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
	
}
