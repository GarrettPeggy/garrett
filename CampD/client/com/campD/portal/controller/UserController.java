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
import com.campD.portal.common.SystemConstant;
import com.campD.portal.common.UserInfoHolder;
import com.campD.portal.model.UserInfo;
import com.campD.portal.service.RoleCacheService;
import com.campD.portal.service.UserService;
import com.campD.portal.util.SystemMessage;
import com.campD.portal.util.WebUtil;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleCacheService roleCacheService;
	
	@RequestMapping("/toLogin.do")
    public String toLogin(){
		
        return "user/login";
    }
	
	/**
     * 登录
     * @param response
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping("/login.do")
    @ResponseBody
    public JSONView login(HttpServletResponse response, HttpServletRequest request) throws Exception {
        
        JSONView jv = new JSONView();
        Map<String, Object> map = bindParamToMap(request);
        map.put("ip", WebUtil.getIPAddress(request));//设置ip
        
        //调用登录接口
        Map<?, ?> userInfoMap = userService.getUserInfoByMdn(map);
        String returnCode = (String)userInfoMap.get("returnCode");
        
        if (null == userInfoMap.get("userInfo")) {//返回为空时
            jv.setFail();
            jv.setSearchReturnType();
            jv.setReturnMsg("该用户未注册,请注册！");
            return jv;
        }
        
        //WebUtil.removeSession(request, SystemConstant.LOGIN_RAND);//从session移除验证码
        UserInfo userInfo = userService.setUserInfo(request, (Map) userInfoMap.get("userInfo"));//设置userInfo
        WebUtil.addSession(request, SystemConstant.USER_INFO, userInfo);// 把用户信息放入session中
        UserInfoHolder.set(userInfo);//存放userInfo
        
        // 返回登陆成功信息
        jv.setReturnCode(returnCode);
        jv.setSearchReturnType();
        jv.setReturnMsg((String)userInfoMap.get("returnMsg"));
        
        return jv;
    }
    
    @RequestMapping("/toRegister.do")
    public String toRegister(){
		
        return "user/register";
    }
    
    /**
     * 用户注册
     * @param response
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping("/register.do")
    @ResponseBody
    public JSONView register(HttpServletResponse response, HttpServletRequest request) throws Exception {
    	
    	JSONView jv = new JSONView();
        
    	Map<String, Object> map = bindParamToMap(request);
    	
    	// 初次进来的用户给它设置默认角色
    	String commonRoleId = roleCacheService.searchRoleIdByName(new String(SystemMessage.getString("role_common").getBytes("iso8859-1"), "UTF-8"));
    	map.put("roleId", commonRoleId);
    	
    	//调用登录接口
        Map<?, ?> userInfoMap = userService.getUserInfoByMdn(map);
        
        if (null != userInfoMap.get("userInfo")) {//返回为空时
            jv.setFail();
            jv.setSearchReturnType();
            jv.setReturnMsg("该手机号已注册！");
            return jv;
        }
    	
        Map<?, ?> resultMap = userService.register(map);
        UserInfo userInfo = userService.setUserInfo(request, (Map) resultMap.get("userInfo"));//设置userInfo
        WebUtil.addSession(request, SystemConstant.USER_INFO, userInfo);// 把用户信息放入session中
        UserInfoHolder.set(userInfo);//存放userInfo
        
        return getSearchJSONView(resultMap);
    }
    
    /**
     * 退出登录页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/quit.do")
    public String quit(HttpServletResponse response, HttpServletRequest request){
        
        request.getSession().invalidate();
        
        return "redirect:/";
    }
	
}
