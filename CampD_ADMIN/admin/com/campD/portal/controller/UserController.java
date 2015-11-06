/**
 * 用户管理
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.common.JSONView;
import com.campD.portal.common.PageInfo;
import com.campD.portal.common.SystemConstant;
import com.campD.portal.common.UserInfoHolder;
import com.campD.portal.model.UserInfo;
import com.campD.portal.service.RoleCacheService;
import com.campD.portal.service.UserService;
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
        Map<?, ?> userInfoMap = userService.getUserInfoByMdnAndUserName(map);
        String returnCode = (String)userInfoMap.get("returnCode");
        
      //返回为空时，当用户不存在时
        if (null == userInfoMap.get("userInfo")) {
            jv.setReturnCode(SystemConstant.ERROR_CODE_USER_NOT_REGISTER);
            jv.setSearchReturnType();
            jv.setReturnMsg("您的用户名或者密码错误！");
            return jv;
        }
        
        UserInfo userInfo = userService.setUserInfo(request, (Map) userInfoMap.get("userInfo"));//设置userInfo
        // 判断用户当前权限，加入当前用户不是管理员，那么返回用户无权限登陆
        if(!SystemConstant.ROLE_ADMIN.equals(userInfo.getRoleName()) && !SystemConstant.ROLE_SUPER_ADMIN.equals(userInfo.getRoleName())){
        	jv.setReturnCode(SystemConstant.ERROR_CODE_AUTH_ADMIN);
            jv.setSearchReturnType();
            jv.setReturnMsg("对不起，您不是管理员，只有管理员才能登陆！");
            return jv;
        }
        
        WebUtil.addSession(request, SystemConstant.USER_INFO, userInfo);// 把用户信息放入session中
        UserInfoHolder.set(userInfo);//存放userInfo
        
        // 返回登陆成功信息
        jv.setReturnCode(returnCode);
        jv.setSearchReturnType();
        jv.setReturnMsg((String)userInfoMap.get("returnMsg"));
        
        return jv;
    }
    
    @RequestMapping("/toList.do")
    public String toList(){
		
        return "user/userList";
    }
    
    /**
	 * 用户列表--查询
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/list.do")
	public String queryUserList(HttpServletResponse response, ModelMap mop, HttpServletRequest request) {
	    
		Map reqMap = bindParamToMap(request);
		PageInfo pageInfo = getPageInfo(request);
		Map userListMap = userService.getUserList(reqMap, pageInfo);
		mop.addAttribute("userListMap", userListMap);
		
		return "user/userListCtx";
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
        
        return "user/login";
    }
    
    /**
     * 更新用户信息的界面
     * 
     */
    @RequestMapping("/toUpdateUserInfo.do")
    public String toUpdateUserInfo(HttpServletRequest request) {
		
        return "user/userInfoDialog";
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/updateRole.do")
    @ResponseBody
    public Map updateRole(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		
		// 初次进来的用户给它设置默认角色
    	String roleId = roleCacheService.searchRoleIdByName((String) reqMap.get("roleName"));
    	reqMap.put("roleId", roleId);
    	Map returnMap = userService.updateRole(reqMap); 
		
		return getOperateJSONView(returnMap);
    }
    
    @SuppressWarnings("rawtypes")
	@RequestMapping("/updateUserInfo.do")
    @ResponseBody
    public Map updateUserInfo(HttpServletRequest request) {
    	
		Map reqMap = bindParamToMap(request);
		
		Map returnMap = userService.updateUserInfo(reqMap);
		if(SystemConstant.RETURN_SUCC.equals((String) returnMap.get("returnCode"))){
			UserInfo userInfo = UserInfoHolder.get();
			userInfo.setEmail((String) reqMap.get("email"));
			userInfo.setMdn((String) reqMap.get("mdn"));
			userInfo.setUserName((String) reqMap.get("userName"));
			UserInfoHolder.set(userInfo);
			WebUtil.addSession(request, SystemConstant.USER_INFO, userInfo);// 把用户信息放入session中
		}
		
		return getOperateJSONView(returnMap);
    }
	
}
