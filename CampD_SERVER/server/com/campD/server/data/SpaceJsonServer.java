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
        
        jsonView.setReturnMsg("场地添加成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("后台场地发布成功,\\(^o^)/。。。，updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	
	/**
	 * 查询场地列表信息
	 * @param reqMap:{name：场地名称，adress：场地地址，workFor：使用哪些活动(是就是范畴id)，capacity：场地容量，cost：花费,:spaceLevel:场地级别,spaceType:场地类型}
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
			if("全部".equals(reqMap.get("adress").toString())){  // TODO:使用空字符串“”
				sqlStr+=" order by t1.adress asc ";
			}else{
				sqlStr+=" and t1.adress like '%"+reqMap.get("adress")+"%' ";
			}
		}
		if(null!=reqMap.get("workFor") && !"".equals(reqMap.get("workFor"))){ // TODO:严格搜索
			sqlStr+=" and t1.work_for like '%"+reqMap.get("workFor")+"%' ";
		}
		if(null!=reqMap.get("capacity") && !"".equals(reqMap.get("capacity"))){ // TODO:删掉
			sqlStr+=" and t1.capacity like '%"+reqMap.get("capacity")+"%' ";
		}
		if(null!=reqMap.get("cost") && !"".equals(reqMap.get("cost"))){
			//sqlStr+=" and t1.cost like '%"+reqMap.get("cost")+"%' ";
			if(Integer.parseInt(reqMap.get("cost").toString())==1){ // TODO:条件加括号， 使用"1".equals()
				sqlStr+=" and t1.cost is null or t1.cost=0 "; 
			}
			if(Integer.parseInt(reqMap.get("cost").toString())==2){
				sqlStr+=" and t1.cost is not null ";
			}
		}
		
		if(null!=reqMap.get("spaceType") && !"".equals(reqMap.get("spaceType"))){
			if(Integer.parseInt(reqMap.get("spaceType").toString())==0){
				sqlStr+=" order by t1.space_type asc ";
			}
			if(Integer.parseInt(reqMap.get("spaceType").toString())==1){
				sqlStr+=" and t1.space_type=0 ";
			}
			if(Integer.parseInt(reqMap.get("spaceType").toString())==2){
				sqlStr+=" and t1.space_type=1 ";
			}
			if(Integer.parseInt(reqMap.get("spaceType").toString())==3){
				sqlStr+=" and t1.space_type=2 ";
			}
			if(Integer.parseInt(reqMap.get("spaceType").toString())==4){
				sqlStr+=" and t1.space_type=3 ";
			}
			if(Integer.parseInt(reqMap.get("spaceType").toString())==5){
				sqlStr+=" and t1.space_type=4 ";
			}
		}
		
		if(null!=reqMap.get("minCapacity") && !"".equals(reqMap.get("minCapacity")) && null!=reqMap.get("maxCapacity") && !"".equals(reqMap.get("maxCapacity"))){
			if(-1==Integer.parseInt(reqMap.get("minCapacity").toString()) && -1==Integer.parseInt(reqMap.get("maxCapacity").toString())){
				sqlStr+=" order by t1.capacity asc ";
			}
			if(10==Integer.parseInt(reqMap.get("minCapacity").toString()) && 30==Integer.parseInt(reqMap.get("maxCapacity").toString())){
				sqlStr+=" and t1.capacity>=10 and t1.capacity<30 order by t1.capacity asc ";
			}
			if(30==Integer.parseInt(reqMap.get("minCapacity").toString()) && 50==Integer.parseInt(reqMap.get("maxCapacity").toString())){
				sqlStr+=" and t1.capacity>=30 and t1.capacity<50 order by t1.capacity asc ";
			}
			if(50==Integer.parseInt(reqMap.get("minCapacity").toString()) && 70==Integer.parseInt(reqMap.get("maxCapacity").toString())){
				sqlStr+=" and t1.capacity>=50 and t1.capacity<70 order by t1.capacity asc ";
			}
			if(70==Integer.parseInt(reqMap.get("minCapacity").toString()) && 90==Integer.parseInt(reqMap.get("maxCapacity").toString())){
				sqlStr+=" and t1.capacity>=70 and t1.capacity<90 order by t1.capacity asc ";
			}
			if(90==Integer.parseInt(reqMap.get("minCapacity").toString()) && 110==Integer.parseInt(reqMap.get("maxCapacity").toString())){
				sqlStr+=" and t1.capacity>=90 and t1.capacity<110 order by t1.capacity asc ";
			}
		}
		
		
		if(null!=reqMap.get("spaceLevel") && !"".equals(reqMap.get("spaceLevel"))){
			sqlStr+=" and t1.space_level="+reqMap.get("spaceLevel")+" ";
		}
		
		//首页查询如果flag=0就是精品场地查询出
		if(null!=reqMap.get("flag") && 0==Integer.parseInt(reqMap.get("flag").toString())){
			sqlStr+=" and t1.space_level=1 order by t1.cost asc limit 0,3 ";
		}
		
		List resultList = jdbcTemplate.queryForList(sqlStr, new Object[]{});
		
    	JSONView jsonView = new JSONView();
    	jsonView.setSuccess();
    	jsonView.setReturnMsg("场地查询成功");
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
