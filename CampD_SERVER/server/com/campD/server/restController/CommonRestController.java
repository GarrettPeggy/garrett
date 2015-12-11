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

import com.campD.server.data.CommonJsonServer;

/**
 * @author Garrett Wang
 * 公共管理接口
 */
@RestController
@RequestMapping(value="/common")
public class CommonRestController extends BaseRestController {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private CommonJsonServer commonJsonServer;
	
	/**
	 * 添加系统配置信息
	 */
    @SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/addSysConfig", method=RequestMethod.POST)
    @ResponseBody
    public Map addSysConfig(HttpServletRequest request) {
    	
    	Map reqMap = bindParamToMap(request);
    	Map returnMap = commonJsonServer.addSysConfig(reqMap);
    	
        return returnMap;
    }
    
    /**
	 * 更新系统配置信息
	 */
    @SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/updateSysConfig", method=RequestMethod.POST)
    @ResponseBody
    public Map updateSysConfig(HttpServletRequest request) {
    	
    	Map reqMap = bindParamToMap(request);
    	Map returnMap = commonJsonServer.updateSysConfig(reqMap);
    	
        return returnMap;
    }
    
    /**
	 * 添加无用图片配置信息
	 */
    @SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/addNoUsePic", method=RequestMethod.POST)
    @ResponseBody
    public Map addNoUsePic(HttpServletRequest request) {
    	
    	Map reqMap = bindParamToMap(request);
    	Map returnMap = commonJsonServer.addNoUsePic(reqMap);
    	
        return returnMap;
    }
    
    /**
	 * 查找系统配置信息
	 */
    @SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/findSysConfigs", method=RequestMethod.POST)
    @ResponseBody
    public Map findSysConfigs(HttpServletRequest request) {
    	
    	Map reqMap = bindParamToMap(request);
    	Map returnMap = commonJsonServer.findSysConfigs(reqMap);
    	
        return returnMap;
    }
}
