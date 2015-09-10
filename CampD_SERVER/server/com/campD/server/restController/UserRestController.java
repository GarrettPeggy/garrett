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

import com.campD.server.data.UserJsonServer;
/**
 * @author Administrator
 *
 */

@RestController
@RequestMapping(value="/user")
public class UserRestController extends BaseRestController{
	
	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserJsonServer userJsonServer;
    
    @SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/register", method=RequestMethod.POST)
    @ResponseBody
    public Map register(HttpServletRequest request) {
    	
    	Map reqMap = bindParamToMap(request);
    	logger.info("reqMap->"+reqMap);
    	Map returnMap = userJsonServer.register(reqMap);
    	
        return returnMap;
    }
}