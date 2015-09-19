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
 * 场地service端接口
 * @author qinrunhua
 *
 */
@Repository
public class SpaceJsonServer {
	protected Logger logger = Logger.getLogger(getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * 后台场地发布
	 * @param reqMap:{creatorId：创建者id，createTime：创建时间，name：场地名称，adress：场地地址，traggic：交通状况，workFor：使用哪些活动，capacity：场地容量，spaceType：场地类型，contactor：场地联系人，cost：花费，contact：联系方式，showImages：场地展示图片，description：场地描述,spaceLevel:场地级别}
	 * spaceLevel:场地级别(0:普通场地，1：精品场地)
	 * @return
	 */
	public Map add(Map reqMap){
		logger.info("reqMap="+reqMap);
		// 添加场地到数据库
		//String sqlStr = "insert into activity(id,creator_id,category_id,act_num,act_city,act_type,requirement,create_time,status) values(?,?,?,?,?,?,?,?,?)";
		String sqlStr = " insert into spaces(id,creator_id,name,adress,traffic,work_for,capacity,cost,contact,show_images,description,create_time,space_type,contactor,space_level) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("creatorId"), reqMap.get("name"), reqMap.get("adress"),reqMap.get("traggic"),reqMap.get("workFor"),reqMap.get("capacity"),reqMap.get("cost"),reqMap.get("contact"),reqMap.get("showImages"),reqMap.get("description"),new Date(),reqMap.get("spaceType"),reqMap.get("contactor"),reqMap.get("spaceLevel")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("后台场地发布失败，呵呵。。。，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setSuccess();
        jsonView.setReturnMsg("场地添加成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("后台场地发布成功,\\(^o^)/。。。，updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	/**
	 * 查询场地列表信息
	 * @param reqMap:{name：场地名称，adress：场地地址，workFor：使用哪些活动(是就是范畴id)，capacity：场地容量，cost：花费}
	 * @return
	 */
	public Map getSpaceInfoList(Map reqMap){
		logger.info("reqMap="+reqMap);
		//根据场地名称，场地地地址，使用哪些活动，场地容量，花费等信息查询场地列表信息.
		String sqlStr = "select t1.id,t1.creator_id,t1.name,t1.adress,t1.traffic,t1.work_for,t1.capacity,t1.cost,t1.contact,t1.show_images,t1.description,t1.create_time,t1.space_type,t1.contactor,t1.space_level from spaces t1 where 1=1 ";
		if(null!=reqMap.get("name") && !"".equals(reqMap.get("name"))){
			sqlStr+=" and t1.name like '%"+reqMap.get("name")+"%' ";
		}
		if(null!=reqMap.get("adress") && !"".equals(reqMap.get("adress"))){
			sqlStr+=" and t1.adress like '%"+reqMap.get("adress")+"%' ";
		}
		if(null!=reqMap.get("workFor") && !"".equals(reqMap.get("workFor"))){
			sqlStr+=" and t1.work_for like '%"+reqMap.get("workFor")+"%' ";
		}
		if(null!=reqMap.get("capacity") && !"".equals(reqMap.get("capacity"))){
			sqlStr+=" and t1.capacity like '%"+reqMap.get("capacity")+"%' ";
		}
		if(null!=reqMap.get("cost") && !"".equals(reqMap.get("cost"))){
			sqlStr+=" and t1.cost like '%"+reqMap.get("cost")+"%' ";
		}
		
		List resultList = jdbcTemplate.queryForList(sqlStr, new Object[]{});
		
    	JSONView jsonView = new JSONView();
    	jsonView.setSuccess();
    	jsonView.setReturnMsg("场地添加成功");
        jsonView.addAttribute("resultList", resultList);
        logger.info("场地列表信息查询,\\(^o^)/。。。，resultList="+resultList);
        
        return jsonView;
	}
	/**
	 * 查询场地详情
	 * @param reqMap:{id:场地id}
	 * @return
	 */
	public Map getSpaceInfoById(Map reqMap){
		logger.info("reqMap="+reqMap);
		//根据场地Id查询场地信息
		String sqlStr = "select t1.id,t1.creator_id,t1.name,t1.adress,t1.traffic,t1.work_for,t1.capacity,t1.cost,t1.contact,t1.show_images,t1.description,t1.create_time,t1.space_type,t1.contactor,t1.space_level from spaces t1 where 1=1 and t1.id=? ";
		Map spaceInfo = jdbcTemplate.queryForMap(sqlStr, new Object[]{reqMap.get("id")});
		
		JSONView jsonView = new JSONView();
		jsonView.setSuccess();
		jsonView.setReturnMsg("场地添加成功");
		jsonView.addAttribute("spaceInfo", spaceInfo);
		logger.info("场地信息查询,\\(^o^)/。。。，" + spaceInfo);
		
		return jsonView;
	}
	
	
}
