/**
 * 
 */
package com.campD.server.data;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.campD.server.common.JSONView;

/**
 * @author Administrator
 *
 */
@Repository
public class RoleJsonServer {
	
	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据角色名称查询该角色详细信息
     * @param reqMap
     * @return 
     */
    @SuppressWarnings({"rawtypes" })
	public Map findRoleByName(Map reqMap) {
		
    	logger.info("reqMap=" + reqMap);
		String sqlStr = "select id,name from role where name=?";
		List resultList = jdbcTemplate.queryForList(sqlStr, new Object[]{reqMap.get("roleName")});

		JSONView jsonView = new JSONView();
		jsonView.addAttribute("roleList", resultList);
        logger.info("resultList=" + resultList);
        
        return jsonView;
	}
    
    /**
     * 根据角色名称查询该角色详细信息
     * 说明：这个查询可以缓存，因为角色不会经常改变
     * @param reqMap
     * @return 
     */
    @SuppressWarnings({ "rawtypes" })
	public Map findRoles() {
    	
		JSONView jsonView = new JSONView();
		
		String sqlStr = "select id,name from role";
		List roleList =  jdbcTemplate.queryForList(sqlStr, new Object[]{});
		jsonView.addAttribute("roleList", roleList);
        logger.info("roleList=" + roleList);
        
        return jsonView;
	}
    
}
