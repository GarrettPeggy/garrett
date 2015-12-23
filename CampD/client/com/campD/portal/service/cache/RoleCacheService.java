/**
 * 
 */
package com.campD.portal.service.cache;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.campD.portal.service.common.JsonClientService;
import com.campD.portal.util.SystemMessage;

/**
 * @author Administrator
 *
 */
@Service("roleCacheService")
public class RoleCacheService extends JsonClientService {

	@SuppressWarnings("rawtypes")
	public Map getAllRoles() {
		
		return postForObject(SystemMessage.getString("roleJsonServer") + "/getAllRoles", null, Map.class, false);
		
    }
	
	@SuppressWarnings("rawtypes")
	public String searchRoleIdByName(String name){
		
		String roleId = null;
		Map rolesMap = getAllRoles();
		List roles = (List) rolesMap.get("roleList");
		
		for (int i = 0; i < roles.size(); i++) {
			Map roleMap = (Map) roles.get(i);
			if(name.equals((String)(roleMap.get("name")))){
				roleId = (String) roleMap.get("id");
				break;
			}
		}
		
		return roleId;
		
	}
}
