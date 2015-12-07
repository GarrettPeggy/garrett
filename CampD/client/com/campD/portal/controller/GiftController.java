/**
 * 
 */
package com.campD.portal.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.campD.portal.common.JSONView;
import com.campD.portal.common.PageInfo;
import com.campD.portal.service.GiftService;
import com.campD.portal.util.FileUtils;
import com.campD.portal.util.SystemMessage;

/**
 * 礼品管理
 * @author Garrett
 *
 */
@Controller
@RequestMapping("/gift")
public class GiftController extends BaseController {

	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private GiftService giftService;
	
	/**
	 * 查询礼品列表
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public JSONView getList(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		PageInfo pageInfo=getPageInfo(request);
		Map<?, ?> giftListMap = giftService.getList(map,pageInfo,Boolean.valueOf(map.get("isUserAuth").toString()));
		
		return getSearchJSONView(giftListMap);
	}
	
	/**
	 * 精美礼品列表页
	 * @throws Exception
	 */
	@RequestMapping("/highLevelList.do")
	public String highLevelList() throws Exception {
		return "gift/high_level";
	}
	
	/**
	 * 礼品首页
	 * @throws Exception
	 */
	@RequestMapping("/indexList.do")
	public String indexList() throws Exception {
		return "gift/gift_index";
	}
	
	/**
	 * 查询礼品详情
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getById.do")
	public String getById(HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = bindParamToMap(request);
		Map<?, ?> giftMap = giftService.getById(map);
		readDscriptionFromRemoteFile(giftMap);
		
		request.setAttribute("giftMap", giftMap);
		 
		return "gift/gift_detail";
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void readDscriptionFromRemoteFile(Map giftMap){
		String description = (String) ((Map)giftMap.get("giftMap")).get("description");
		description = description.replace("\\", "/");
		int index = description.indexOf("attached/html");
		if(null!=description && index>-1){ // 将连接更新为内容
			((Map)giftMap.get("giftMap")).put("description",FileUtils.readRemoteFile(SystemMessage.getString("ossResUrl") + description));
		}
	}
}
