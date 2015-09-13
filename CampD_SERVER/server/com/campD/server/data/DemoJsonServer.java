/**
 * 
 */
package com.campD.server.data;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.campD.server.common.JSONView;

/**
 * @author Administrator
 *
 */
@Repository
public class DemoJsonServer {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "rawtypes"})
	public Map register(Map reqMap) {
		
		String sqlStr = "insert into user(id,name,password) values(?,?,?)";
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("userName"), reqMap.get("password")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        JSONView jsonView = new JSONView();
        jsonView.setSuccess();
        jsonView.addAttribute("updateLineCount", updateLineCount);
        return jsonView;

	}

    @SuppressWarnings({"rawtypes" })
	public Map findUserByUserName(Map reqMap) {
		
		String sqlStr = "select id,name,password from user where name=?";
		Map returnMap = jdbcTemplate.queryForMap(sqlStr, new Object[]{reqMap.get("userName")});
        return returnMap;
        
	}
}
