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
 * @author Administrator
 *
 */
@Service("spaceService")
public class SpaceService extends JsonClientService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map addSpace(Map reqMap) {
		
		return postForMap(SystemMessage.getString("spaceJsonServer") + "/add", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateSpace(Map reqMap) {
		
		return postForMap(SystemMessage.getString("spaceJsonServer") + "/update", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getSpaceById(Map reqMap) {
		
		return postForMap(SystemMessage.getString("spaceJsonServer") + "/getSpaceInfoById", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getSpaceList(Map reqMap, PageInfo pageInfo) {
		
		return postForMap(SystemMessage.getString("spaceJsonServer") + "/getSpaceInfoList", reqMap, pageInfo);
		
    }
}
