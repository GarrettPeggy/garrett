package com.campD.portal.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.campD.portal.service.common.JsonClientService;

@Service("demoCacheService")
public class DemoCacheService extends JsonClientService{

	@SuppressWarnings("rawtypes")
	public Map getGreetingContent() {
		
		//RestTemplate restTemplate = new RestTemplate();
		return postForMap("http://localhost:8080/campD_server/demo/greeting", null);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Map register(Map reqMap) {
		
		//RestTemplate restTemplate = new RestTemplate();
		return postForMap("http://localhost:8080/campD_server/demo/register", reqMap);
    }
}
