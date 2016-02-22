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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.campD.server.common.JSONView;

/**
 * 空间管理
 * @author Garrett
 *
 */
@Repository
public class OfficeJsonServer {

	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 后台空间发布
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public Map add(Map reqMap){
		logger.info("reqMap="+reqMap);
		String sqlStr = " insert into office(id,name,belong_to,type,status,contactor,contact,cost,unit,province,city,area,address,traffic,show_images,description,create_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("name"), reqMap.get("belongTo"), reqMap.get("type"), reqMap.get("status"), reqMap.get("contactor"), reqMap.get("contact"), reqMap.get("cost"), reqMap.get("unit"), reqMap.get("province"),reqMap.get("city"),reqMap.get("area"),reqMap.get("address"),reqMap.get("traffic"),reqMap.get("showImages"),reqMap.get("description"),new Date()};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("后台空间发布发布失败，呵呵。。。，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnMsg("空间添加成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("后台空间发布成功,\\(^o^)/。。。，updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	
	/**
	 * 更新空间信息
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public Map update(Map reqMap){
		
		logger.info("reqMap="+reqMap);
		String sqlStr = " update office set name=?, type=?,contactor=?,contact=?,cost=?,unit=?, province=?, city=?, area=?,address=?,traffic=?,show_images=?,description=?, create_time=? where id=?";
        Object[] params = new Object[]{reqMap.get("name"), reqMap.get("type"), reqMap.get("contactor"), reqMap.get("contact"), reqMap.get("cost"), reqMap.get("unit"), reqMap.get("province"),reqMap.get("city"),reqMap.get("area"),reqMap.get("address"),reqMap.get("traffic"),reqMap.get("showImages"),reqMap.get("description"),new Date(),reqMap.get("id")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("后台更新空间失败，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnMsg("空间更新成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("后台空间更新成功,\\(^o^)/。。。，updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	
	/**
	 * 查询空间详情
	 * @param reqMap:{id:空间id}
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getById(Map reqMap){
		logger.info("reqMap="+reqMap);
		String sqlStr = "select id,name,belong_to,type,status,contactor,contact,cost,unit,province,city,area,address,traffic,show_images,description,create_time from office where id=? ";
		Map officeInfo = jdbcTemplate.queryForMap(sqlStr, new Object[]{reqMap.get("id")});
		
		JSONView jsonView = new JSONView();
		jsonView.setSuccess();
		jsonView.setReturnMsg("空间查询成功");
		jsonView.addAttribute("officeInfo", officeInfo);
		logger.info("空间信息查询,\\(^o^)/。。。，" + officeInfo);
		
		return jsonView;
	}
	
	/**
	 * 查询空间列表信息
	 */
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public Map getList(Map reqMap){
		
		logger.info("reqMap="+reqMap);
		String sqlStr = "select id,name,belong_to,type,status,contactor,contact,cost,unit,province,city,area,address,traffic,show_images,description,create_time from office where 1=1 ";
		String sqlCount =" select count(1) from office where 1=1 ";
		
		Object name = reqMap.get("name");//名称
		if(null!=name && !"".equals(name)){
			sqlStr+=" and name like '%"+name+"%' ";
			sqlCount+=" and name like '%"+name+"%' ";
		}

		Object province = reqMap.get("province");//省份
		if(null!=province && !"".equals(province)){
			sqlStr+=" and province='"+province+"' ";
			sqlCount+=" and province='"+province+"' ";
		}

		Object city = reqMap.get("city");//地级市
		if(null!=city && !"".equals(city)){
			sqlStr+=" and city='"+city+"' ";
			sqlCount+=" and city='"+city+"' ";
		}
		
		Object area = reqMap.get("area");//区域
		if(null!=area && !"".equals(area)){
			sqlStr+=" and area='"+area+"' ";
			sqlCount+=" and area='"+area+"' ";
		}
		
		Object status = reqMap.get("status");//状体
		if(null!=status && !"".equals(status)){
			sqlStr+=" and status='"+status+"' ";
			sqlCount+=" and status='"+status+"' ";
		}
		
		Object contactor = reqMap.get("contactor");//联系人
		if(null!=contactor && !"".equals(contactor)){
			sqlStr+=" and contactor='"+contactor+"' ";
			sqlCount+=" and contactor='"+contactor+"' ";
		}
		
		Object contact = reqMap.get("contact");//联系方式
		if(null!=contact && !"".equals(contact)){
			sqlStr+=" and contact='"+contact+"' ";
			sqlCount+=" and contact='"+contact+"' ";
		}
		
		Object type = reqMap.get("type");//空间类型
		if(null!=type && !"".equals(type)){
			sqlStr+=" and type='"+type+"' ";
			sqlCount+=" and type='"+type+"' ";
		}
		
		Object belongTo = reqMap.get("belongTo");//空间隶属于那个总空间
		if(null!=belongTo && !"".equals(belongTo)){
			sqlStr+=" and belong_to='"+belongTo+"' ";
			sqlCount+=" and belong_to='"+belongTo+"' ";
		}
		
		// 默认按照发布时间降序排列
		sqlStr += " ORDER BY create_time DESC ";

    	logger.info("sql日志输出:sqlCount===="+sqlCount);
		// 获取当前场地总数
		int dataCount = jdbcTemplate.queryForInt(sqlCount);
		
		// 查询的分页参数
    	Map pageInfo = (Map) reqMap.get("pageInfo");
    	int curPage = (int) pageInfo.get("curPage");
    	int pageLimit = (int) pageInfo.get("pageLimit");
    	int startIndex = (curPage-1)*pageLimit;
    	sqlStr += " limit " + startIndex + "," + pageLimit;
    	
    	logger.info("sql日志输出:sqlStr===="+sqlStr);
		
		List resultList = jdbcTemplate.queryForList(sqlStr, new Object[]{});
    	JSONView jsonView = new JSONView();
    	jsonView.setSuccess();
    	jsonView.setReturnMsg("空间列表查询成功");
        jsonView.addAttribute("resultList", resultList);
        jsonView.addAttribute("dataCount", dataCount);
        
        logger.info("空间列表信息查询,\\(^o^)/。。。，resultList="+resultList);
        
        return jsonView;
	}
	
	/**
	 * 更新空间状态
	 */
	@SuppressWarnings("rawtypes")
	public Map updateStatus(Map reqMap){
		
		logger.info("reqMap="+reqMap);
		String sqlStr = " update office set status=? where id=?";
        Object[] params = new Object[]{reqMap.get("status"),reqMap.get("id")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("后台更新空间状态失败，此时传入参数为->params="+params);
			return jsonView;
        }
        
        jsonView.setReturnMsg("空间状态更新成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("后台空间整体更新成功,\\(^o^)/。。。，updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	
}
