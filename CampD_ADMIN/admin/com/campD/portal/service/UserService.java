/**
 * 
 */
package com.campD.portal.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.campD.portal.common.PageInfo;
import com.campD.portal.model.UserInfo;
import com.campD.portal.service.common.JsonClientService;
import com.campD.portal.util.SystemMessage;
import com.campD.portal.util.WebUtil;

/**
 * @author Administrator
 *
 */
@Service("userService")
public class UserService extends JsonClientService {
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getUserInfoByMdn(Map reqMap) {
		
		return postForObject(SystemMessage.getString("userJsonServer") + "/getByMdn", reqMap, Map.class, false);
		
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getUserInfoByMdnAndUserName(Map reqMap) {
		
		return postForObject(SystemMessage.getString("userJsonServer") + "/findUserByMdnAndUserName", reqMap, Map.class, false);
		
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getUserList(Map reqMap, PageInfo pageInfo) {
		
		return postForMap(SystemMessage.getString("userJsonServer") + "/list", reqMap, pageInfo);
		
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateRole(Map reqMap) {
		
		return postForMap(SystemMessage.getString("userJsonServer") + "/updateRole", reqMap);
		
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
   	public Map updateUserInfo(Map reqMap) {
   		
   		return postForMap(SystemMessage.getString("userJsonServer") + "/updateUserInfo", reqMap);
   		
    }
    
    /**
     * 设置用户信息
     * @param request
     * @param userInfoMap
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public UserInfo setUserInfo(HttpServletRequest request, Map userInfoMap) throws Exception {
        
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginIp(WebUtil.getIPAddress(request));
        userInfo.setId((String) userInfoMap.get("id"));
        userInfo.setRoleName((String) userInfoMap.get("roleName"));
        userInfo.setMdn((String) userInfoMap.get("mdn"));
        userInfo.setEmail((String) userInfoMap.get("email"));
        userInfo.setUserName((String) userInfoMap.get("userName"));
        
        return userInfo;
    }
    
}
