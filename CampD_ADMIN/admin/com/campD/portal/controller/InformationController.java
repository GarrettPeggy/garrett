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

import com.campD.portal.service.CommonService;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/information")
public class InformationController extends BaseController {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private CommonService commonService;
	
	/**
	 * 去配置首页轮播图
	 */
	@RequestMapping("/toHomePicConfig.do")
    public String toHomePicConfig(){
		
        return "information/homePicConfig";
    }
	
	/**
	 * 去配置首页通知
	 */
	@RequestMapping("/notify/toList.do")
    public String toNotifyList(){
		
        return "/information/notifyList";
    }
    
    /**
	 * 首页通知列表
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/notify/list.do")
	public String queryUserList(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
	    
		Map reqMap = bindParamToMap(request);
		Map sysConfigMap = commonService.findSysConfigs(reqMap);
		mop.addAttribute("sysConfig", sysConfigMap);
		
		return "/information/notifyListCtx";
	}
	
}
