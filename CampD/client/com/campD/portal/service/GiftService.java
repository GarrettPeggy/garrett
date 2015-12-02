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
 * 礼品服务
 * @author Garrett
 *
 */
@Service("giftService")
public class GiftService extends JsonClientService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getById(Map reqMap) {
		
		return postForMap(SystemMessage.getString("giftJsonServer") + "/getGiftById", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getList(Map reqMap, PageInfo pageInfo, boolean isUserAuth) {
		
		return postForMap(SystemMessage.getString("giftJsonServer") + "/getGiftList", reqMap, pageInfo, null, isUserAuth);
		
    }
}
