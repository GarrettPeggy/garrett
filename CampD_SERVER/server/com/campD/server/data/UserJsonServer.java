/**
 * 
 */
package com.campD.server.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.campD.server.common.JSONView;
import com.campD.server.common.SystemConstant;

/**
 * @author Administrator
 *
 */
@Repository
public class UserJsonServer {
	
	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private RoleJsonServer roleJsonServer;
	
	/**
	 * 用户注册
	 * @param reqMap:{userName:用户名，mdn:手机号，roleId:普通用户角色}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public Map register(Map reqMap) {
		
		JSONView jsonView = new JSONView();
		
		// 记录用户注册信息
		String sqlStr = "insert into user(id,user_name,mdn,role_id,login_time) values(?,?,?,?,?)";
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("userName"), reqMap.get("mdn"), reqMap.get("roleId"), new Date()};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("记录用户注册信息失败，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setSuccess();
        jsonView.setReturnSuccMsg();
        jsonView.addAttribute("updateLineCount", updateLineCount);
        return jsonView;

	}

    /**
     * 根据手机号查找用户信息
     * @param reqMap:{mdn:手机号}
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Map findUserByMdn(Map reqMap) {
		
    	JSONView jsonView = new JSONView();
    	
		String sqlStr = "select u.id, u.user_name as name, u.password, u.mdn, u.email, u.login_time, r.id as roleId, r.name as roleName from user u, role r where u.role_id = r.id and mdn=?";
		List resultList = new ArrayList();
        jdbcTemplate.query(sqlStr, new Object[]{reqMap.get("mdn")}, new RowCallbackHandler() {
			@Override
            public void processRow(ResultSet rs) throws SQLException {
				Map resultMap = new HashMap();
				resultMap.put("id", UUID.fromString(rs.getString("id")));
				resultMap.put("name", rs.getString("name"));
				resultMap.put("password", rs.getString("password"));
				resultMap.put("mdn", rs.getString("mdn"));
				resultMap.put("email", rs.getString("email"));
				resultMap.put("loginTime", rs.getDate("login_time"));
				
				resultMap.put("roleId", UUID.fromString(rs.getString("roleId")));
				resultMap.put("roleName", rs.getString("roleName"));
				resultList.add(resultMap);
            }
			
        });
        jsonView.setSuccess();
        jsonView.setReturnSuccMsg();
        jsonView.addAttribute("userList", resultList);
        logger.info("根据手机号查询有无该用户->mdn="+reqMap.get("mdn"));
        
        return jsonView;
        
	}
}
