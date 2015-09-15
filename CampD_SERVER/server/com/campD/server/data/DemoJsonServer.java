/**
 * 
 */
package com.campD.server.data;
import java.util.Map;
import java.util.UUID;

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
public class DemoJsonServer {
	
	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings({ "rawtypes"})
	public Map register(Map reqMap) {
		
		logger.info("reqMap=" + reqMap);
		
		String sqlStr = "insert into user(id,name,password) values(?,?,?)";
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("userName"), reqMap.get("password")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        JSONView jsonView = new JSONView();
        jsonView.addAttribute("updateLineCount", updateLineCount);
        
        logger.info("updateLineCount=" + updateLineCount);
        
        return jsonView;

	}

    @SuppressWarnings({"rawtypes" })
	public Map findUserByUserName(Map reqMap) {
    	
    	logger.info("reqMap=" + reqMap);
		
		String sqlStr = "select id,name,password from user where name=?";
		Map userInfo = jdbcTemplate.queryForMap(sqlStr, new Object[]{reqMap.get("userName")});
		
		JSONView jsonView = new JSONView();
		jsonView.addAttribute("userInfo", userInfo);
		
		logger.info("userInfo=" + userInfo);
		
        return jsonView;
        
	}
    
    public static void main(String[] args){
    	System.out.println(UUID.randomUUID().toString());
    	System.out.println(UUID.randomUUID().toString());
    }
}
