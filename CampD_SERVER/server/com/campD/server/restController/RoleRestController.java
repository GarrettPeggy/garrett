/**
 * 
 */
package com.campD.server.restController;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.campD.server.data.RoleJsonServer;

/**
 * 角色管理
 * @author 王光华
 * 
 */
@RestController
@RequestMapping(value="/role")
public class RoleRestController extends BaseRestController {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
	private RoleJsonServer roleJsonServer;
	
	/**
     * 获取所有的角色信息
     * @param reqMap:{mdn:手机号}
     * @return
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping("/getAllRoles")
    @ResponseBody
    public Map getAllRoles() {
    	
    	Map returnMap = roleJsonServer.findRoles();
    	
        return returnMap;
    }
	
}
