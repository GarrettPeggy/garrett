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
import com.campD.portal.service.UserCacheService;
import com.campD.portal.service.UserService;

/**
 * @author Garrett Wang
 *
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserCacheService userCacheService;
	
	@RequestMapping("/index.do")
    public String index(){
		
		UserInfoHolder.set(new UserInfo());
		
        return "demo/rest-angular";
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/greeting.do")
    @ResponseBody
    public Map greeting(HttpServletRequest request) {
		
		UserInfoHolder.set(new UserInfo());
		
		return userCacheService.getGreetingContent();
    }
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/register.do")
    @ResponseBody
    public Map register(HttpServletRequest request) {
		
		UserInfoHolder.set(new UserInfo());
		Map reqMap = bindParamToMap(request);
		
		return userService.register(reqMap);
    }
}
