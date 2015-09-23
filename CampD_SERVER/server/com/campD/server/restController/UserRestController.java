/**
 * 
 */
package com.campD.server.restController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.campD.server.data.UserJsonServer;
/**
 * @author Administrator
 *
 */

@RestController
@RequestMapping(value="/user")
public class UserRestController extends BaseRestController{
	
	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserJsonServer userJsonServer;
    
	/**
	 * 用户注册
	 */
    @SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/register", method=RequestMethod.POST)
    @ResponseBody
    public Map register(HttpServletRequest request) {
    	
    	Map reqMap = bindParamToMap(request);
    	Map returnMap = userJsonServer.register(reqMap);
    	
        return returnMap;
    }
    
    /**
	 * 更新用户角色
	 */
    @SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/updateRole", method=RequestMethod.POST)
    @ResponseBody
    public Map updateRole(HttpServletRequest request) {
    	
    	Map reqMap = bindParamToMap(request);
    	Map returnMap = userJsonServer.updateRole(reqMap);
    	
        return returnMap;
    }
    
    /**
	 * 更新用户基本信息
	 */
    @SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
    @ResponseBody
    public Map updateUserInfo(HttpServletRequest request) {
    	
    	Map reqMap = bindParamToMap(request);
    	Map returnMap = userJsonServer.updateUserInfo(reqMap);
    	
        return returnMap;
    }
    
    /**
     * 根据手机号查找用户信息
     * @param reqMap:{mdn:手机号}
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping("/getByMdn")
    @ResponseBody
    public Map getByMdn(HttpServletRequest request) {
    	
    	Map reqMap = bindParamToMap(request);
    	Map returnMap = userJsonServer.findUserByMdn(reqMap);
    	
        return returnMap;
    }
    
    /**
     * 根据手机号查找用户信息
     * @param reqMap:{mdn:手机号}
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping("/list")
    @ResponseBody
    public Map getList(HttpServletRequest request) {
    	
    	Map reqMap = bindParamToMap(request);
    	Map returnMap = userJsonServer.findUserList(reqMap);
    	
        return returnMap;
    }
}