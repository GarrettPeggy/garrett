package com.campD.portal.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.campD.portal.common.PageInfo;
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
	public Map updateActType(Map reqMap){
		return postForMap(SystemMessage.getString("activityJsonServer") + "/updateActType", reqMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateClick(Map reqMap){
		return postForMap(SystemMessage.getString("activityJsonServer") + "/updateClick", reqMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getActivityList(Map reqMap,PageInfo pageInfo){
		return postForMap(SystemMessage.getString("activityJsonServer") + "/getActivityList", reqMap, pageInfo);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getActivityById(Map reqMap){
		return postForObject(SystemMessage.getString("activityJsonServer") + "/getActivityById", reqMap,Map.class, true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map takeAnActive(Map reqMap){
		return postForObject(SystemMessage.getString("activityJsonServer") + "/takeAnActive", reqMap,Map.class, true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getMyTakeAnActive(Map reqMap,PageInfo pageInfo){
		return postForMap(SystemMessage.getString("activityJsonServer") + "/getMyTakeAnActive", reqMap, pageInfo);
	}
	
}