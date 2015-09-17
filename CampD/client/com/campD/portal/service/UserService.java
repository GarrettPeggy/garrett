/**
 * 
 */
package com.campD.portal.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

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
	public Map register(Map reqMap) {
		
		return postForMap(SystemMessage.getString("userJsonServer") + "/add", reqMap);
		
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getUserInfoByMdn(Map reqMap) {
		
		return postForMap(SystemMessage.getString("userJsonServer") + "/getByMdn", reqMap);
		
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
        userInfo.setUserame((String) userInfoMap.get("userName"));
        
        return userInfo;
    }
    
}
