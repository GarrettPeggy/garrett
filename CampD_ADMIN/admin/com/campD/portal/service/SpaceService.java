/**
 * 
 */
package com.campD.portal.service;

import java.util.Map;

import org.springframework.stereotype.Service;

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
}
