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
 * 礼品服务类
 * @author Garrett
 *
 */
@Service("giftService")
public class GiftService extends JsonClientService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map add(Map reqMap) {
		
		return postForMap(SystemMessage.getString("giftJsonServer") + "/add", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map update(Map reqMap) {
		
		return postForMap(SystemMessage.getString("giftJsonServer") + "/update", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getById(Map reqMap) {
		
		return postForMap(SystemMessage.getString("giftJsonServer") + "/getGiftById", reqMap);
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getList(Map reqMap, PageInfo pageInfo) {
		
		return postForMap(SystemMessage.getString("giftJsonServer") + "/getGiftList", reqMap, pageInfo);
		
    }
	
	/**
	 * 更新礼品级别：精美礼品，普通礼品
	 * @param reqMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map updateLevel(Map reqMap) {
		
		return postForMap(SystemMessage.getString("giftJsonServer") + "/updateLevel", reqMap);
		
    }
}
