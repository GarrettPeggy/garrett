/**
 * 
 */
package com.campD.server.data;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.campD.server.common.JSONView;
import com.campD.server.util.SystemMessage;

/**
 * @author Administrator
 *
 */
@Repository
public class UserJsonServer {
	
	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	/**
	 * 目前测试账户统一初始化密码为111111，统一角色为普通角色
	 * 用户注册
	 * @param reqMap:{userName:用户名，mdn:手机号，roleId:普通用户角色}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public Map register(Map reqMap) {
		
		logger.info("reqMap="+reqMap);
		
		// 记录用户注册信息
		String sqlStr = "insert into user(id,user_name,password,mdn,role_id,login_time,register_time) values(?,?,?,?,?,?,?)";
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("userName"), SystemMessage.getString("inital_password"),reqMap.get("mdn"), reqMap.get("roleId"), new Date(), new Date()};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("记录用户注册信息失败，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        Map userInfoMap = findUserByMdn(reqMap);
        jsonView.addAttribute("userInfo", userInfoMap.get("userInfo"));
        
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("updateLineCount="+updateLineCount);
        
        return jsonView;

	}

    /**
     * 根据手机号查找用户信息
     * @param reqMap:{mdn:手机号}
     * @return
     */
    @SuppressWarnings({"rawtypes" })
	public Map findUserByMdn(Map reqMap) {
		
    	logger.info("reqMap="+reqMap);
    	JSONView jsonView = new JSONView();
    	Map resultMap = null;
    	
		String sqlStr = "select u.id, u.user_name as userName, u.password, u.mdn, u.email, u.login_time, u.register_time, u.status, r.id as roleId, r.name as roleName from user u, role r where u.role_id = r.id and mdn=?";
		try {  
			resultMap = jdbcTemplate.queryForMap(sqlStr, new Object[]{reqMap.get("mdn")});  
        } catch (EmptyResultDataAccessException e) { 

            jsonView.addAttribute("userInfo", resultMap);
            logger.info("resultMap="+resultMap);
            return jsonView;  
        }
		
		jsonView.addAttribute("userInfo", resultMap);
        logger.info("resultMap="+resultMap);
        return jsonView;
        
	}
}
