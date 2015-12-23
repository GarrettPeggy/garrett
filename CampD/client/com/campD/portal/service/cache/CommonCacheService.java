/**
 * 公共服务
 */
package com.campD.portal.service.cache;

import java.util.Map;

import org.springframework.stereotype.Service;
import com.campD.portal.service.common.JsonClientService;
import com.campD.portal.util.SystemMessage;

/**
 * @author Administrator
 * 公共服务，例如系统的一些配置信息，无效图片配置等等
 */
@Service("commonCacheService")
public class CommonCacheService extends JsonClientService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map createSysConfig(Map reqMap) {
		
		return postForMap(SystemMessage.getString("commonJsonServer") + "/addSysConfig", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateSysConfig(Map reqMap) {
		
		return postForMap(SystemMessage.getString("commonJsonServer") + "/updateSysConfig", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map findSysConfigs(Map reqMap) {
		
		return postForObject(SystemMessage.getString("commonJsonServer") + "/findSysConfigs", reqMap, Map.class, false);
		
    }
}
