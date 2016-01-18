/**
 * 
 */
package com.campD.portal.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.campD.portal.common.PageInfo;
import com.campD.portal.service.common.JsonClientService;
import com.campD.portal.util.SystemMessage;

/**
 * @author Garrett
 *
 */
@Service("officeSpaceService")
public class OfficeSpaceService extends JsonClientService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map add(Map reqMap) {
		
		return postForMap(SystemMessage.getString("officeSpaceJsonServer") + "/add", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map update(Map reqMap) {
		
		return postForMap(SystemMessage.getString("officeSpaceJsonServer") + "/update", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getById(Map reqMap) {
		
		return postForMap(SystemMessage.getString("officeSpaceJsonServer") + "/getById", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getList(Map reqMap, PageInfo pageInfo) {
		
		return postForMap(SystemMessage.getString("officeSpaceJsonServer") + "/getList", reqMap, pageInfo);
		
    }
}
