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
import com.campD.portal.service.SpaceService;

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
	@SuppressWarnings("rawtypes")
	@RequestMapping("/add.do")
    @ResponseBody
	public JSONView add(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = spaceService.add(map);
		 
		return getSearchJSONView(resultMap);
	}
	
	/**
	 * 查询场地列表信息
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getSpaceInfoList.do")
    @ResponseBody
	public JSONView getSpaceInfoList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = spaceService.getSpaceInfoList(map);
		 
		return getSearchJSONView(resultMap);
	}
	
	/**
	 * 查询场地详情
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/getSpaceInfoById.do")
    @ResponseBody
	public JSONView getSpaceInfoById(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		
		Map<?, ?> resultMap = spaceService.getSpaceInfoById(map);
		 
		return getSearchJSONView(resultMap);
	}
	
}
