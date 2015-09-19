package com.campD.portal.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.campD.portal.service.common.JsonClientService;
import com.campD.portal.util.SystemMessage;
/**
 * 活动客户端service
 * @author qrh
 *
 */
@Service("activityService")
public class ActivityService extends JsonClientService {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map add(Map reqMap){
		return postForMap(SystemMessage.getString("activityJsonServer") + "/add", reqMap);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map update(Map reqMap){
		return postForMap(SystemMessage.getString("activityJsonServer") + "/update", reqMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateClick(Map reqMap){
		return postForMap(SystemMessage.getString("activityJsonServer") + "/updateClick", reqMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getActivityList(Map reqMap){
		return postForMap(SystemMessage.getString("activityJsonServer") + "/getActivityList", reqMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getActivityById(Map reqMap){
		return postForMap(SystemMessage.getString("activityJsonServer") + "/getActivityById", reqMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map takeAnActive(Map reqMap){
		return postForMap(SystemMessage.getString("activityJsonServer") + "/takeAnActive", reqMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getMyTakeAnActive(Map reqMap){
		return postForMap(SystemMessage.getString("activityJsonServer") + "/getMyTakeAnActive", reqMap);
	}
	
	
}
