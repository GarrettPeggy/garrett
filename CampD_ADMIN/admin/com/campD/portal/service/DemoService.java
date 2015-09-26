package com.campD.portal.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.campD.portal.service.common.JsonClientService;
import com.campD.portal.util.SystemMessage;

@Service("demoService")
public class DemoService extends JsonClientService{

	@SuppressWarnings("rawtypes")
	public Map greeting() {
		
		return postForMap("http://localhost:8080/campD_server/demo/greeting", null);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map register(Map reqMap) {
		
		return postForMap(SystemMessage.getString("userJsonServer") + "/add", reqMap);
    }
}
