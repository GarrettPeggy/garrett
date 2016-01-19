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
import com.campD.portal.service.OfficeService;
import com.campD.portal.util.FileUtils;
import com.campD.portal.util.SystemMessage;

/**
 * @author Garrett
 *
 */
@Controller
@RequestMapping("/office")
public class OfficeController extends BaseController {

	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private OfficeService officeService;

	/**
	 * 总空间列表页
	 * @throws Exception
	 */
	@RequestMapping("/toList.do")
	public String toList(HttpServletRequest request) throws Exception {
		bindParamToAttrbute(request);
		return "officeSpace/spaceList";
	}
	
	/**
	 * 查询空间列表
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public JSONView getList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		PageInfo pageInfo=getPageInfo(request);
		Map<?, ?> officeListMap = officeService.getList(map,pageInfo);
		
		return getSearchJSONView(officeListMap);
	}
	
	/**
	 * 查询空间详情
	 */
	@RequestMapping("/getById.do")
	public String getById(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		Map<?, ?> officeMap = officeService.getById(map);
		readDscriptionFromRemoteFile(officeMap);
		
		request.setAttribute("officeMap", officeMap);
		 
		return "officeSpace/detail";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void readDscriptionFromRemoteFile(Map officeMap){
		String description = (String) ((Map)officeMap.get("officeInfo")).get("description");
		description = description.replace("\\", "/");
		int index = description.indexOf("attached/html");
		if(null!=description && index>-1){ // 将连接更新为内容
			((Map)officeMap.get("officeInfo")).put("description",FileUtils.readRemoteFile(SystemMessage.getString("ossResUrl") + description));
		}
	}
	
}
