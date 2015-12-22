/**
 * 
 */
package com.campD.server.data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.campD.server.common.JSONView;

/**
 * @author Garrett Wang
 *
 */
@Repository
public class CommonJsonServer {

	protected Logger logger = Logger.getLogger(getClass());

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	/**
	 * 添加系统配置信息
	 * @param reqMap:{type:配置类型，key:配置项键值，value:配置项值}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public Map addSysConfig(Map reqMap) {
		
		logger.info("reqMap="+reqMap);
		
		// 记录用户注册信息
		String sqlStr = "insert into sys_config(id,sys_type,key_val,value_val,url_val) values(?,?,?,?,?)";
		logger.info("sqlStr="+sqlStr);
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("type"), reqMap.get("key"), reqMap.get("value"), reqMap.get("url")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("添加配置项信息失败->params="+params.toString());
			return jsonView;
        }
        
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("updateLineCount="+updateLineCount);
        
        return jsonView;

	}
	
	/**
	 * 更新系统配置信息
	 * @param reqMap:{type:配置类型，key:配置项键值，value:配置项值}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public Map updateSysConfig(Map reqMap) {
		
		logger.info("reqMap="+reqMap);
		
		// 记录用户注册信息
		String sqlStr = "update sys_config set value_val=?, url_val=? where sys_type=? and key_val=?";
		logger.info("sqlStr="+sqlStr);
        Object[] params = new Object[]{reqMap.get("value"), reqMap.get("url"), reqMap.get("type"), reqMap.get("key")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("更新配置项信息失败->params="+params.toString());
			return jsonView;
        }
        
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("updateLineCount="+updateLineCount);
        
        return jsonView;

	}
	
	/**
     * 根据类型和key查找配置信息
     * @param reqMap:{type:配置信息的类型：例如首页轮播图，key：配置项键值}
     * @return
     */
    @SuppressWarnings({"rawtypes" })
	public Map findSysConfigs(Map reqMap) {
		
    	logger.info("reqMap="+reqMap);
    	String sqlStr = "select id,sys_type,key_val,value_val,url_val from sys_config where 1=1";
    	
    	// 查询的表单参数
    	Object type = reqMap.get("type");
    	Object key = reqMap.get("key");
    	
    	if (null!=type && !"".equals((String) type)) {  
    		sqlStr += " and sys_type = '" + type + "'";
        }
    	if (null!=key && !"".equals((String) key)) {  
    		sqlStr += " and key_val = '" + key + "'";
        }
    	// 默认按照发布时间降序排列
    	sqlStr += " ORDER BY key_val ";
    	logger.info("sqlStr="+sqlStr);
    	
    	JSONView jsonView = new JSONView();
    	List sysConfigList = jdbcTemplate.queryForList(sqlStr, new Object[0]);
		jsonView.addAttribute("sysConfigList", sysConfigList);
        logger.info("sysConfigList="+sysConfigList);
        
        return jsonView;
        
	}
	
	/**
	 * 添加系无用图片信息
	 * @param reqMap:{url:图片URL}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public Map addNoUsePic(Map reqMap) {
		
		logger.info("reqMap="+reqMap);
		
		// 记录用户注册信息
		String sqlStr = "insert into no_use_pic(id,url) values(?,?)";
		logger.info("sqlStr="+sqlStr);
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("url")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("添加无用图片配置信息错误->params="+params.toString());
			return jsonView;
        }
        
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("updateLineCount="+updateLineCount);
        
        return jsonView;

	}
}
