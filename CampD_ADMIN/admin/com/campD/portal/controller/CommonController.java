/**
 * 公共服务
 */
package com.campD.portal.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.service.CommonService;

/**
 * @author Administrator
 * 公共服务
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private CommonService commonService;
	
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/addSysConfig.do")
    @ResponseBody
    public Map addSysConfig(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		
		return commonService.addSysConfig(reqMap);
    }
	
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/updateSysConfig.do")
    @ResponseBody
    public Map updateSysConfig(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		
		return commonService.updateSysConfig(reqMap);
    }
	
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/findSysConfigs.do")
    @ResponseBody
    public Map findSysConfigs(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		
		return commonService.findSysConfigs(reqMap);
    }
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/addNoUsePic.do")
    @ResponseBody
    public Map addNoUsePic(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		
		return commonService.addNoUsePic(reqMap);
    }
}
