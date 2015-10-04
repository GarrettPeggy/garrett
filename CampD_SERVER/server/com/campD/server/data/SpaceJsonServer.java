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
	@SuppressWarnings("rawtypes")
	public Map add(Map reqMap){
		logger.info("reqMap="+reqMap);
		// 添加场地到数据库
		//String sqlStr = "insert into activity(id,creator_id,category_id,act_num,act_city,act_type,requirement,create_time,status) values(?,?,?,?,?,?,?,?,?)";
		String sqlStr = " insert into spaces(id,creator_id,name,adress,traffic,work_for,capacity,cost,contact,show_images,description,create_time,space_type,contactor,space_level) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("creatorId"), reqMap.get("name"), reqMap.get("adress"),reqMap.get("traffic"),reqMap.get("workFor"),reqMap.get("capacity"),reqMap.get("cost"),reqMap.get("contact"),reqMap.get("showImages"),reqMap.get("description"),new Date(),reqMap.get("spaceType"),reqMap.get("contactor"),reqMap.get("spaceLevel")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("后台场地发布失败，呵呵。。。，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnMsg("场地添加成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("后台场地发布成功,\\(^o^)/。。。，updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	
	/**
	 * 更新场地信息
	 * @param reqMap:{id:场地id，name：场地名称，adress：场地地址，traffic：交通状况，workFor：使用哪些活动，capacity：场地容量，spaceType：场地类型，contactor：场地联系人，cost：花费，contact：联系方式，showImages：场地展示图片，description：场地描述}
	 * spaceLevel:场地级别(0:普通场地，1：精品场地)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map update(Map reqMap){
		
		logger.info("reqMap="+reqMap);
		
		// 添加场地到数据库
		String sqlStr = " update spaces set name=?, adress=?, traffic=?, work_for=?, capacity=?, cost=?, contact=?, show_images=?, description=?, space_type=?, contactor=? where id=?";
        Object[] params = new Object[]{reqMap.get("name"), reqMap.get("adress"),reqMap.get("traffic"),reqMap.get("workFor"),reqMap.get("capacity"),reqMap.get("cost"),reqMap.get("contact"),reqMap.get("showImages"),reqMap.get("description"),reqMap.get("spaceType"),reqMap.get("contactor"),reqMap.get("id")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("后台更新场地失败，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnMsg("场地更新成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("后台场地更新成功,\\(^o^)/。。。，updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	
	/**
	 * 更新场地级别
	 * @param {id：场地id，spaceLevel：场地级别}
	 * spaceLevel:场地级别(0:普通场地，1：精品场地)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map updateLevel(Map reqMap){
		
		logger.info("reqMap="+reqMap);
		
		// 添加场地到数据库
		String sqlStr = " update spaces set space_level=? where id=?";
        Object[] params = new Object[]{reqMap.get("spaceLevel"),reqMap.get("id")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("后台更新场地失败，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnMsg("场地级别更新成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("后台场地级别更新成功,\\(^o^)/。。。，updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	
	/**
	 * 查询场地列表信息
	 * @param reqMap:{name：场地名称，adress：场地地址，workFor：使用哪些活动(是就是范畴id)，capacity：场地容量，cost：花费,:spaceLevel:场地级别,spaceType:场地类型}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public Map getSpaceInfoList(Map reqMap){
		
		logger.info("reqMap="+reqMap);
		
		//根据场地名称，场地地地址，适用哪些活动，场地容量，花费等信息查询场地列表信息.  王光华另加查询条件：
		String sqlStr = " select t1.id,t1.creator_id,t1.name,t1.adress,t1.traffic,t1.work_for,t1.capacity,t1.cost,t1.contact,t1.show_images,t1.description,t1.create_time,t1.space_type,t1.contactor,t1.space_level from spaces t1 where 1=1 ";
		String sqlCount =" select count(1) from spaces t1 where 1=1 ";
		
		Object name = reqMap.get("name");//名称
		if(null!=name && !"".equals(name)){
			sqlStr+=" and t1.name like '%"+name+"%' ";
			sqlCount+=" and t1.name like '%"+name+"%' ";
		}

		Object contactor = reqMap.get("contactor");//联系人
		if(null!=contactor && !"".equals(contactor)){
			sqlStr+=" and t1.contactor like '%"+contactor+"%' ";
			sqlCount+=" and t1.contactor like '%"+contactor+"%' ";
		}

		Object contact = reqMap.get("contact");//联系方式
		if(null!=contact && !"".equals(contact)){
			sqlStr+=" and t1.contact ="+contact+" ";
			sqlCount+=" and t1.contact ="+contact+" ";
		}

		Object adress = reqMap.get("adress");//地址
		if(null!=adress && !"".equals(adress)){
			sqlStr+=" and t1.adress like '%"+adress+"%' ";
			sqlCount+=" and t1.adress like '%"+adress+"%' ";
		}

		Object workFor = reqMap.get("workFor");//适用活动
		if(null!=workFor && !"".equals(workFor)){
			sqlStr+=" and t1.work_for ="+workFor+" ";
			sqlCount+=" and t1.work_for ="+workFor+" ";
		}

		Object cost = reqMap.get("cost");//名称
		if(null!=cost && !"".equals(cost)){
			//cost："1"表示免费   "2"表示收费
			if("1".equals(cost.toString())){
				sqlStr+=" and t1.cost is null or t1.cost=0 ";
				sqlCount+=" and t1.cost is null or t1.cost=0 ";
			}
			if("2".equals(cost.toString())){
				sqlStr+=" and t1.cost is not null ";
				sqlCount+=" and t1.cost is not null ";
			}
		}

		Object spaceType = reqMap.get("spaceType");//场地类型
		if(null!=spaceType && !"".equals(spaceType)){
			sqlStr+=" and t1.space_type="+spaceType;
			sqlCount+=" and t1.space_type="+spaceType;
		}

		Object minCapacity = reqMap.get("minCapacity");//最小容量
		if(null!=minCapacity && !"".equals(minCapacity)){
			sqlStr+=" and t1.capacity>="+minCapacity;
			sqlCount+=" and t1.capacity>="+minCapacity;
		}

		Object maxCapacity = reqMap.get("maxCapacity");//最大容量
		if(null!=maxCapacity && !"".equals(maxCapacity)){
			sqlStr+=" and t1.capacity<"+maxCapacity;
			sqlCount+=" and t1.capacity<"+maxCapacity;
		}

		Object beginCreateTime = reqMap.get("beginCreateTime");//最大容量
		Object endCreateTime = reqMap.get("endCreateTime");//场地特征
		if(null!=beginCreateTime && !"".equals(beginCreateTime)){
			sqlStr+=" and t1.create_time>='"+beginCreateTime + " 00:00:00'";
			sqlCount+=" and t1.create_time>='"+beginCreateTime + " 00:00:00'";
		}
		if(null!=endCreateTime && !"".equals(endCreateTime)){
			sqlStr+=" and t1.create_time<'"+endCreateTime + " 23:59:59'";
			sqlCount+=" and t1.create_time<'"+endCreateTime + " 23:59:59'";
		}
		
		Object spaceLevel = reqMap.get("spaceLevel");//场地特征
		if(null!=spaceLevel && !"".equals(spaceLevel)){
			sqlStr+=" and t1.space_level="+spaceLevel+" ";
			sqlCount+=" and t1.space_level="+spaceLevel+" ";
		}
		
		// 获取当前场地总数
		int dataCount = jdbcTemplate.queryForInt(sqlCount);
		
		// 查询的分页参数
    	Map pageInfo = (Map) reqMap.get("pageInfo");
    	int curPage = (int) pageInfo.get("curPage");
    	int pageLimit = (int) pageInfo.get("pageLimit");
    	int startIndex = (curPage-1)*pageLimit;
    	sqlStr += " limit " + startIndex + "," + pageLimit;
    	
    	logger.info("sql日志输出:sqlStr===="+sqlStr);
    	logger.info("sql日志输出:sqlCount===="+sqlCount);
		
		List resultList = jdbcTemplate.queryForList(sqlStr, new Object[]{});
    	JSONView jsonView = new JSONView();
    	jsonView.setSuccess();
    	jsonView.setReturnMsg("场地查询成功");
        jsonView.addAttribute("resultList", resultList);
        jsonView.addAttribute("dataCount", dataCount);
        
        logger.info("场地列表信息查询,\\(^o^)/。。。，resultList="+resultList);
        
        return jsonView;
	}
	/**
	 * 查询场地详情
	 * @param reqMap:{id:场地id}
	 * @return
	 */
	@SuppressWarnings("rawtypes")
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
