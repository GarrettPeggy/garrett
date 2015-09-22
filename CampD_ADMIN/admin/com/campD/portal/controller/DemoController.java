/**
 * 
 */
package com.campD.portal.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.common.UserInfoHolder;
import com.campD.portal.model.UserInfo;
import com.campD.portal.service.DemoCacheService;
import com.campD.portal.service.DemoService;

/**
 * @author Garrett Wang
 *
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{
	
	@Autowired
	private DemoService demoService;
	
	@Autowired
	private DemoCacheService demoCacheService;
	
	@RequestMapping("/index.do")
    public String index(){
		
		UserInfoHolder.set(new UserInfo());
		
        return "user/login";
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/greeting.do")
    @ResponseBody
    public Map greeting(HttpServletRequest request) {
		
		UserInfoHolder.set(new UserInfo());
		
		return demoCacheService.getGreetingContent();
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/register.do")
    @ResponseBody
    public Map register(HttpServletRequest request) {
		
		UserInfoHolder.set(new UserInfo());
		Map reqMap = bindParamToMap(request);
		
		return demoService.register(reqMap);
    }
}
