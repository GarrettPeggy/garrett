/**
 * 
 */
package com.campD.server.data;

import java.util.Date;
import java.util.List;
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
		String sqlStr = "insert into user(id,user_name,password,mdn,role_id,login_time, register_time) values(?,?,?,?,?,?,?)";
		logger.info("sqlStr="+sqlStr);
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
	 * 更新用户角色
	 * @param reqMap:{userId:用户id，roleId：角色id}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public Map updateRole(Map reqMap) {
		
		logger.info("reqMap="+reqMap);
		
		// 更新用户角色
		String sqlStr = "UPDATE user SET role_id=? WHERE id=?";
		logger.info("sqlStr="+sqlStr);
        Object[] params = new Object[]{reqMap.get("roleId"), reqMap.get("userId")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("更新用户角色失败->params="+params.toString());
			return jsonView;
        }
        
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("updateLineCount="+updateLineCount);
        
        return jsonView;

	}
	
	/**
	 * 更新用户基本信息
	 * @param reqMap:{userId:用户id，userName：用户姓名，mdn：手机号，email：邮箱}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public Map updateUserInfo(Map reqMap) {
		
		logger.info("reqMap="+reqMap);
		
		// 更新用户角色
		String sqlStr = "UPDATE user SET user_name=?, mdn=?, email=? WHERE id=?";
		logger.info("sqlStr="+sqlStr);
        Object[] params = new Object[]{reqMap.get("userName"), reqMap.get("mdn"), reqMap.get("email"), reqMap.get("userId")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("更新用户信息失败->params="+params.toString());
			return jsonView;
        }
        
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
    	
		String sqlStr = "select u.id, u.user_name as userName, u.password, u.mdn, u.email, FROM_UNIXTIME(UNIX_TIMESTAMP(u.login_time), '%Y-%m-%d %H:%i:%S') AS login_time,FROM_UNIXTIME(UNIX_TIMESTAMP(u.register_time), '%Y-%m-%d %H:%i:%S') AS register_time, u.status, r.id as roleId, r.name as roleName from user u, role r where u.role_id = r.id and mdn=?";
		logger.info("sqlStr="+sqlStr);
		try {  
			resultMap = jdbcTemplate.queryForMap(sqlStr, new Object[]{reqMap.get("mdn")});  
        } catch (EmptyResultDataAccessException e) { 

            jsonView.addAttribute("userInfo", resultMap);
            logger.info("resultMap="+resultMap);
            return jsonView;  
        }
		
        // 返回查询结果
		jsonView.addAttribute("userInfo", resultMap);
        logger.info("resultMap="+resultMap);
        return jsonView;
        
	}
    
    /**
     * 根据手机号和用户名查找用户信息
     * @param reqMap:{mdn:手机号, userName:用户名}
     * @return
     */
    @SuppressWarnings({"rawtypes" })
	public Map findUserByMdnAndUserName(Map reqMap) {
		
    	logger.info("reqMap="+reqMap);
    	JSONView jsonView = new JSONView();
    	Map resultMap = null;
    	
		String sqlStr = "select u.id, u.user_name as userName, u.password, u.mdn, u.email, FROM_UNIXTIME(UNIX_TIMESTAMP(u.login_time), '%Y-%m-%d %H:%i:%S') AS login_time,FROM_UNIXTIME(UNIX_TIMESTAMP(u.register_time), '%Y-%m-%d %H:%i:%S') AS register_time, u.status, r.id as roleId, r.name as roleName from user u, role r where u.role_id = r.id and mdn=? and u.user_name=?";
		logger.info("sqlStr="+sqlStr);
		try {  
			resultMap = jdbcTemplate.queryForMap(sqlStr, new Object[]{reqMap.get("mdn"), reqMap.get("userName")});  
        } catch (EmptyResultDataAccessException e) { 

            jsonView.addAttribute("userInfo", resultMap);
            logger.info("resultMap="+resultMap);
            return jsonView;  
        }
		
		// 如果记录不为空说明用户已注册，可以允许登陆，此时需要修改登录时间
		String logTimeSqlStr = "UPDATE user SET login_time=? WHERE mdn=?";
		logger.info("logTimeSqlStr="+logTimeSqlStr);
        Object[] params = new Object[]{new Date(), reqMap.get("mdn")};
        int updateLineCount = jdbcTemplate.update(logTimeSqlStr, params);
        
        JSONView logTimeJsonView = new JSONView();
        if(updateLineCount <= 0){
        	logTimeJsonView.setFail();
        	logTimeJsonView.setReturnErrorMsg();
			logger.info("更新用户登录时间失败->params="+params.toString());
			return logTimeJsonView;
        }
		
        // 返回查询结果
		jsonView.addAttribute("userInfo", resultMap);
        logger.info("resultMap="+resultMap);
        return jsonView;
        
	}
    
    /**
     * 根据手机号查找用户信息
     * @param reqMap:{mdn:手机号,userName:用户名，开始注册时间，结束注册时间，开始登陆时间，结束登陆时间}
     * @return
     */
    @SuppressWarnings({"rawtypes" })
	public Map findUserList(Map reqMap) {
		
    	logger.info("reqMap="+reqMap);
    	String sqlStr = "select u.id, u.user_name as userName, u.mdn, u.email, FROM_UNIXTIME(UNIX_TIMESTAMP(u.login_time), '%Y-%m-%d %H:%i:%S') AS login_time,FROM_UNIXTIME(UNIX_TIMESTAMP(u.register_time), '%Y-%m-%d %H:%i:%S') AS register_time, u.status, r.id as roleId, r.name as roleName from user u, role r where u.role_id = r.id";
    	String sqlCountStr = "select count(1) from user u, role r where u.role_id = r.id";
    	
    	// 查询的表单参数
    	String userName = (String) reqMap.get("userName");
    	String mdn = (String) reqMap.get("mdn");
    	String beginRegTime = (String) reqMap.get("beginRegTime");
    	String endRegTime = (String) reqMap.get("endRegTime");
    	String beginLoginTime = (String) reqMap.get("beginLoginTime");
    	String endLoginTime = (String) reqMap.get("endLoginTime");
    	
    	if (!"".equals(userName)) {  
    		sqlStr += " and u.user_name like '%"+userName+"%' ";
    		sqlCountStr += " and u.user_name like '%"+userName+"%' ";
        }
    	if (!"".equals(mdn)) {  
    		sqlStr += " and u.mdn = '" + mdn + "'";
    		sqlCountStr += " and u.mdn = '" + mdn + "'";
        }
    	if (!"".equals(beginRegTime)) {  
    		sqlStr += " and u.register_time >= '" + beginRegTime + " 00:00:00'";
    		sqlCountStr += " and u.register_time >= '" + beginRegTime + " 00:00:00'";
        }
    	if (!"".equals(endRegTime)) {  
    		sqlStr += " and u.register_time <= '" + endRegTime + " 23:59:59'";
    		sqlCountStr += " and u.register_time <= '" + endRegTime + " 23:59:59'";
        }
    	if (!"".equals(beginLoginTime)) {  
    		sqlStr += " and u.login_time >= '" + beginLoginTime + " 00:00:00'";
    		sqlCountStr += " and u.login_time >= '" + beginLoginTime + " 00:00:00'";
        }
    	if (!"".equals(endLoginTime)) {  
    		sqlStr += " and u.login_time <= '" + endLoginTime + " 23:59:59'";
    		sqlCountStr += " and u.login_time <= '" + endLoginTime + " 23:59:59'";
        }

    	logger.info("sqlCountStr="+sqlCountStr);
    	// 获取当前用户总数
    	@SuppressWarnings("deprecation")
		int dataCount = jdbcTemplate.queryForInt(sqlCountStr);
    	
    	// 查询的分页参数
    	Map pageInfo = (Map) reqMap.get("pageInfo");
    	int curPage = (int) pageInfo.get("curPage");
    	int pageLimit = (int) pageInfo.get("pageLimit");
    	int startIndex = (curPage-1)*pageLimit;
    	sqlStr += " limit " + startIndex + "," + pageLimit;

    	logger.info("sqlStr="+sqlStr);
    	
    	JSONView jsonView = new JSONView();
    	List userList = jdbcTemplate.queryForList(sqlStr, new Object[0]);
		jsonView.addAttribute("userList", userList);
		jsonView.addAttribute("dataCount", dataCount);
        logger.info("userList="+userList);
        
        return jsonView;
        
	}
    
}
