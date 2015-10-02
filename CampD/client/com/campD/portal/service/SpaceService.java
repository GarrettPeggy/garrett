package com.campD.portal.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.campD.portal.common.PageInfo;
import com.campD.portal.service.common.JsonClientService;
import com.campD.portal.util.SystemMessage;
/**
 * 场地客户端接口
 * @author qrh
 *
 */
@Service("spaceService")
public class SpaceService extends JsonClientService {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map add(Map reqMap){
		return postForMap(SystemMessage.getString("spaceJsonServer") + "/add", reqMap);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getSpaceInfoList(Map reqMap,PageInfo pageInfo,boolean isUserAuth){
		return postForMap(SystemMessage.getString("spaceJsonServer") + "/getSpaceInfoList", reqMap,pageInfo, null, isUserAuth);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getSpaceInfoById(Map reqMap){
		return postForObject(SystemMessage.getString("spaceJsonServer") + "/getSpaceInfoById", reqMap, Map.class, false);
	}
}
