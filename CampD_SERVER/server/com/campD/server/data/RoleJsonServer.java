/**
 * 
 */
package com.campD.server.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

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
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map findRoleByName(Map reqMap) {
		
		String sqlStr = "select id,name from role where name=?";
		Map returnMap = new HashMap();
		List resultList = new ArrayList();
        jdbcTemplate.query(sqlStr, new Object[]{reqMap.get("roleName")}, new RowCallbackHandler() {
			@Override
            public void processRow(ResultSet rs) throws SQLException {
				Map resultMap = new HashMap();
				resultMap.put("id", UUID.fromString(rs.getString("id")));
				resultMap.put("name", rs.getString("name"));
				resultList.add(resultMap);
            }
        });
        returnMap.put("roleList", resultList);
        logger.info("根据角色名查询角色信息->roleName="+reqMap.get("roleName"));
        
        return returnMap;
	}
    
    /**
     * 根据角色名称查询该角色详细信息
     * 说明：这个查询可以缓存，因为角色不会经常改变
     * @param reqMap
     * @return 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map findRoles(Map reqMap) {
		
		String sqlStr = "select id,name from role";
		Map returnMap = new HashMap();
		List resultList = new ArrayList();
        jdbcTemplate.query(sqlStr, new Object[]{}, new RowCallbackHandler() {
			@Override
            public void processRow(ResultSet rs) throws SQLException {
				Map resultMap = new HashMap();
				resultMap.put("id", UUID.fromString(rs.getString("id")));
				resultMap.put("name", rs.getString("name"));
				resultList.add(resultMap);
            }
        });
        returnMap.put("roleList", resultList);
        logger.info("根据角色名查询角色信息->roleName="+reqMap.get("roleName"));
        
        return returnMap;
	}
    
}
