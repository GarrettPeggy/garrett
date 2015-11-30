/**
 * 
 */
package com.campD.server.restController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.campD.server.data.GiftJsonServer;

/**
 * 
 * @author Garrett
 * 礼品接口
 */
@RestController
@RequestMapping(value="/gift")
public class GiftRestController extends BaseRestController {

	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private GiftJsonServer giftJsonServer;
	
	/**
	 * 后台礼品发布
	 * @param reqMap:{}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/add", method=RequestMethod.POST)
    @ResponseBody
	public Map add(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = giftJsonServer.add(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 后台礼品更新
	 * @param reqMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/update", method=RequestMethod.POST)
    @ResponseBody
	public Map update(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = giftJsonServer.update(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 后台礼品级别更新
	 * @param reqMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/updateLevel", method=RequestMethod.POST)
    @ResponseBody
	public Map updateLevel(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = giftJsonServer.updateLevel(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 查询礼品列表信息
	 * @param reqMap:{}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/getGiftList", method=RequestMethod.POST)
    @ResponseBody
	public Map getGiftList(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = giftJsonServer.getGiftList(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 查询礼品详情
	 * @param reqMap:{id:礼品id}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/getGiftById", method=RequestMethod.POST)
    @ResponseBody
	public Map getGiftById(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = giftJsonServer.getGiftById(reqMap);
    	
        return returnMap;
	}
}
