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
@Service("officeService")
public class OfficeService extends JsonClientService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map add(Map reqMap) {
		
		return postForMap(SystemMessage.getString("officeJsonServer") + "/add", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map update(Map reqMap) {
		
		return postForMap(SystemMessage.getString("officeJsonServer") + "/update", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateStatus(Map reqMap) {
		
		return postForMap(SystemMessage.getString("officeJsonServer") + "/updateStatus", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getById(Map reqMap) {
		
		return postForMap(SystemMessage.getString("officeJsonServer") + "/getById", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getList(Map reqMap, PageInfo pageInfo) {
		
		return postForMap(SystemMessage.getString("officeJsonServer") + "/getList", reqMap, pageInfo);
		
    }
}
