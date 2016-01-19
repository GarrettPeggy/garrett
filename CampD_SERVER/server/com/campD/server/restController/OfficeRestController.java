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

import com.campD.server.data.OfficeJsonServer;

/**
 * @author Garrett
 * 子空间总管理
 */
@RestController
@RequestMapping(value="/office")
public class OfficeRestController extends BaseRestController {

	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private OfficeJsonServer officeJsonServer;
	
	/**
	 * 后台空间发布
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/add", method=RequestMethod.POST)
    @ResponseBody
	public Map add(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = officeJsonServer.add(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 后台空间更新
	 * @param reqMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/update", method=RequestMethod.POST)
    @ResponseBody
	public Map update(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = officeJsonServer.update(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 查询空间列表信息
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/getList", method=RequestMethod.POST)
    @ResponseBody
	public Map getList(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = officeJsonServer.getList(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 查询空间详情
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/getById", method=RequestMethod.POST)
    @ResponseBody
	public Map getById(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = officeJsonServer.getById(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 后台空间状体更新
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/updateStatus", method=RequestMethod.POST)
    @ResponseBody
	public Map updateStatus(HttpServletRequest request){
		
		Map reqMap = bindParamToMap(request);
    	Map returnMap = officeJsonServer.updateStatus(reqMap);
    	
        return returnMap;
	}
	
}
