/**
 * 公共服务
 */
package com.campD.portal.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.service.CommonService;
import com.campD.portal.service.cache.CommonCacheService;


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
	
	@Autowired
	private CommonCacheService commonCacheService;
	
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/addSysConfig.do")
    @ResponseBody
    public Map addSysConfig(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		Map returnMap = commonCacheService.createSysConfig(reqMap);
		
		return getOperateJSONView(returnMap);
    }
	
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/updateSysConfig.do")
    @ResponseBody
    public Map updateSysConfig(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		Map returnMap = commonCacheService.updateSysConfig(reqMap);
		
		return getOperateJSONView(returnMap);
    }
	
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping("/findSysConfigs.do")
    @ResponseBody
    public Map findSysConfigs(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		
		return commonCacheService.findSysConfigs(reqMap);
    }
	
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/addNoUsePic.do")
    @ResponseBody
    public Map addNoUsePic(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		
		return commonService.addNoUsePic(reqMap);
    }
	
	/**
	 * 测试微信JSSDK签名接口
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/toMap.do")
	public String toMap(HttpServletResponse response, ModelMap mop,  HttpServletRequest request) {
		
	    Map reqMap = bindParamToMap(request);
	    String val = (String) reqMap.get("address");
	    try {
			val = new String(val.getBytes("iso8859-1"), "UTF-8");
		    mop.put("address", val);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	    
		return "common/baidumap";
	}
}
