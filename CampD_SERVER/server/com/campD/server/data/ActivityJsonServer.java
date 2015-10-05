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
import com.campD.server.common.SystemConstant;

/**
 * 活动server端接口
 * @author qrh
 *
 */
@Repository
public class ActivityJsonServer {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 添加活动需求
	 * @param reqMap:{categoryId：活动所属范畴id，actNum:活动人数，actCity：活动城市，actType:活动类型,requirement：活动需求，creator：活动提交人，createTime：活动提交时间，status：活动状态}
	 *  actType:活动类型指普通活动，热门活动  0是普通活动，1是热门活动
	 * @return
	 */
	public Map add(Map reqMap) {
		
		logger.info("reqMap="+reqMap);
		
		// 添加活动需求到数据库
		String sqlStr = "insert into activity(id,creator_id,category_id,act_num,adress,sponsor,act_city,act_type,requirement,show_image,title,sub_title,begin_time,end_time,create_time,publish_time,status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("creator"), reqMap.get("categoryId"), reqMap.get("actNum"),reqMap.get("adress"),reqMap.get("sponsor"),reqMap.get("actCity"),reqMap.get("actType"),reqMap.get("requirement"),reqMap.get("showImage"),reqMap.get("title"),reqMap.get("subTitle"),reqMap.get("beginTime"),reqMap.get("endTime"),new Date(),new Date(),Integer.parseInt(SystemConstant.ACTIVITY_NOT_PUBLISH)};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("添加活动需求失败，呵呵。。。，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
        jsonView.setReturnMsg("活动需求添加成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	/**
	 * 活动更新
	 * @param reqMap:{categoryId：活动所属范畴id，actNum:活动人数，actCity：活动城市，actType:活动类型,requirement：活动需求，adress:活动地址，sponsor：活动发起方，status：活动状态，show_image：活动展示的图片，title：活动标题，subTitle：活动副标题，beginTime:开始时间，endTime：结束时间，publishTime：发布时间,id:活动Id}
	 * @return
	 */
	public Map update(Map reqMap){
		
		logger.info("reqMap="+reqMap);
		//活动更新
		String sqlStr = " update activity set category_id=?,act_num=?,act_city=?,act_type=?,requirement=?,adress=?,sponsor=?,status=?,show_image=?,title=?,sub_title=?,begin_time=?,end_time=?,publish_time=? where id=? ";
        Object[] params = new Object[]{reqMap.get("categoryId"), reqMap.get("actNum"), reqMap.get("actCity"),reqMap.get("actType"),reqMap.get("requirement"),reqMap.get("adress"),reqMap.get("sponsor"),reqMap.get("status"),reqMap.get("showImage"),reqMap.get("title"),reqMap.get("subTitle"),reqMap.get("beginTime"),reqMap.get("endTime"),reqMap.get("publishTime"),reqMap.get("id")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("活动更新失败，呵呵。。。，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
        jsonView.setReturnMsg("活动发布成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("updateLineCount="+updateLineCount);
        return jsonView;
	}
	
	/**
	 * 更新活动类型
	 * @param reqMap:{act_type:活动类型，id：活动id}
	 * @return
	 */
	public Map updateActType(Map reqMap){
		logger.info("reqMap="+reqMap);
		String sqlStr = " update activity set act_type=? where 1=1 and id=? ";
		Object[] params = new Object[]{reqMap.get("actType"),reqMap.get("id")};
		int updateLineCount = jdbcTemplate.update(sqlStr, params);
	    JSONView jsonView = new JSONView();
	    if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("活动类型更新失败，呵呵。。。，此时传入参数为->params="+params.toString());
			return jsonView;
        }
	    jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
        jsonView.setReturnMsg("活动类型更新成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("updateLineCount="+updateLineCount);
        return jsonView;
	}
	
	/**
	 * 更新活动点击次数
	 * @param reqMap:{id:活动id}
	 * @return
	 */
	public Map updateClick(Map reqMap){
		logger.info("reqMap="+reqMap);
		//活动点击次数更新
		String sqlStr = " update activity set click_num=click_num+1 where id=? ";
        Object[] params = new Object[]{reqMap.get("id")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("活动点击次数更新失败，呵呵。。。，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
        jsonView.setReturnMsg("活动次数增加成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("updateLineCount="+updateLineCount);
        return jsonView;
	}
	/**
	 * 根据活动所属范畴id,活动提交时间，活动状态等信息查询活动
	 * @param reqMap:{categoryId：活动所属范畴id，createTime：活动提交时间，status：活动状态,actType:活动类型,creatorId:活动创建者Id}
	 * @return
	 */
	public Map getActivityList(Map reqMap){
		//System.out.println("reqMap.get(\"categoryId\")===================="+reqMap.get("categoryId"));
		logger.info("reqMap="+reqMap);
		String sqlStr = " select id,creator_id,category_id,act_num,adress,sponsor,act_city,act_type,requirement,assistance,show_image,title,sub_title,date_format(ifnull(begin_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as begintime,date_format(ifnull(end_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as endtime,click_num,date_format(ifnull(create_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as createtime,date_format(ifnull(publish_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as publishtime,status from activity where 1=1 ";
		String sqlCount =" select count(1) from activity where 1=1 ";
		
		if(null!=reqMap.get("id") && !"".equals(reqMap.get("id"))){
			sqlStr+=" and id ='"+reqMap.get("id")+"' ";
			sqlCount+=" and id ='"+reqMap.get("id")+"' ";
		}
		
		if(null!=reqMap.get("title") && !"".equals(reqMap.get("title"))){
			sqlStr+=" and title like '%"+reqMap.get("title")+"%' ";
			sqlCount+=" and title like '%"+reqMap.get("title")+"%' ";
		}
		
		if(null!=reqMap.get("adress") && !"".equals(reqMap.get("adress"))){
			sqlStr+=" and adress like '%"+reqMap.get("adress")+"%' ";
			sqlCount+=" and adress like '%"+reqMap.get("adress")+"%' ";
		}
		
		if(null!=reqMap.get("actCity") && !"".equals(reqMap.get("actCity"))){
			sqlStr+=" and act_city like '%"+reqMap.get("actCity")+"%' ";
			sqlCount+=" and act_city like '%"+reqMap.get("actCity")+"%' ";
		}
		
		if(null!=reqMap.get("sponsor") && !"".equals(reqMap.get("sponsor"))){
			sqlStr+=" and sponsor like '%"+reqMap.get("sponsor")+"%' ";
			sqlCount+=" and sponsor like '%"+reqMap.get("sponsor")+"%' ";
		}
		
		if(null!=reqMap.get("beginTime") && !"".equals(reqMap.get("beginTime"))){
			sqlStr+=" and begin_time >= '"+reqMap.get("beginTime")+"' ";
			sqlCount+=" and begin_time >= '"+reqMap.get("beginTime")+"' ";
		}
		
		if(null!=reqMap.get("endTime") && !"".equals(reqMap.get("endTime"))){
			sqlStr+=" and end_time <= '"+reqMap.get("endTime")+"' ";
			sqlCount+=" and end_time <= '"+reqMap.get("endTime")+"' ";
		}
		
		if(null!=reqMap.get("categoryId") && !"".equals(reqMap.get("categoryId"))){
			sqlStr+=" and category_id ="+reqMap.get("categoryId")+"";
			sqlCount+=" and category_id ="+reqMap.get("categoryId")+"";
		}
		if(null!=reqMap.get("createTime") && !"".equals(reqMap.get("createTime"))){
			sqlStr+=" and create_time like '%"+reqMap.get("createTime")+"%' ";
			sqlCount+=" and create_time like '%"+reqMap.get("createTime")+"%' ";
		}
		if(null!=reqMap.get("status") && !"".equals(reqMap.get("status"))){
			sqlStr+=" and status ="+reqMap.get("status")+" ";
			sqlCount+=" and status ="+reqMap.get("status")+" ";
		}
		if(null!=reqMap.get("creatorId") && !"".equals(reqMap.get("creatorId"))){
			sqlStr+=" and creator_id ='"+reqMap.get("creatorId")+"' ";
			sqlCount+=" and creator_id ='"+reqMap.get("creatorId")+"' ";
		}
		
		if(null!=reqMap.get("actType") && !"".equals(reqMap.get("actType"))){
			sqlStr+=" and act_type ="+reqMap.get("actType")+" order by begin_time desc ";
			sqlCount+=" and act_type ="+reqMap.get("actType")+" order by begin_time desc ";
		}
		
		// 获取当前活动总数
		int dataCount = jdbcTemplate.queryForInt(sqlCount);
		
		// 查询的分页参数
    	Map pageInfo = (Map) reqMap.get("pageInfo");
    	int curPage = (int) pageInfo.get("curPage");
    	int pageLimit = (int) pageInfo.get("pageLimit");
    	int startIndex = (curPage-1)*pageLimit;
    	sqlStr += " limit " + startIndex + "," + pageLimit;
    	
    	logger.info("sql日志输出:sqlStr===="+sqlStr);
    	logger.info("sql日志输出:sqlCount===="+sqlCount);
		
		List activityList =  jdbcTemplate.queryForList(sqlStr, new Object[]{});//多条信息
		JSONView jsonView = new JSONView();
		jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
        jsonView.setReturnMsg("活动查询成功");
		jsonView.addAttribute("activityList", activityList);
		jsonView.addAttribute("dataCount", dataCount);
		
        logger.info("activityList=" + activityList);
        return jsonView;
	}
	
	/**
	 * 根据Id查询活动详情
	 * @param reqMap:{id：活动id}
	 * @return
	 */
	public Map getActivityById(Map reqMap){
		logger.info("reqMap="+reqMap);
		String sqlStr = " select id,creator_id,category_id,act_num,adress,sponsor,act_city,act_type,requirement,assistance,show_image,title,sub_title,date_format(ifnull(begin_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as begintime,date_format(ifnull(end_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as endtime,click_num,date_format(ifnull(create_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as createtime,date_format(ifnull(publish_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as publishtime,status from activity where 1=1 and id=? ";
		Map activityInfo = jdbcTemplate.queryForMap(sqlStr, new Object[]{reqMap.get("id")});//单条信息
		JSONView jsonView = new JSONView();
		jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
        jsonView.setReturnMsg("活动次数增加成功");
		jsonView.addAttribute("activityInfo", activityInfo);
		logger.info("activityInfo=" + activityInfo);
        return jsonView;
	}
	
	/**
	 * 用户参加活动
	 * @param reqMap:{categoryId：活动id，userId：参与活动的人的id，enrollTime：报名时间}
	 * @return
	 */
	public Map takeAnActive(Map reqMap){
		logger.info("reqMap="+reqMap);
		// 用户参与活动，添加数据到数据库
		String sqlStr = "insert into user_activity(id,user_id,activity_id,enroll_time) values(?,?,?,?)";
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("userId"), reqMap.get("activityId"), new Date()};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("用户参与活动失败，呵呵。。。，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
        jsonView.setReturnMsg("活动报名成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	
	/**
	 * 查询我的报名活动
	 * @param reqMap:{userId：当前用户id}
	 * @return
	 */
	public Map getMyTakeAnActive(Map reqMap){
		logger.info("reqMap="+reqMap);
		String sqlStr = " select t1.id,t1.creator_id,t1.category_id,t1.act_num,t1.adress,t1.sponsor,t1.act_city,t1.act_type,t1.requirement,t1.assistance,t1.show_image,t1.title,t1.sub_title,date_format(ifnull(t1.begin_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as begintime,date_format(ifnull(t1.end_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as endtime,t1.click_num,date_format(ifnull(t1.create_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as createtime,date_format(ifnull(t1.publish_time,'0000-00-00 00:00:00'),'%Y-%m-%d %H:%i:%s') as publishtime,t1.status from activity t1 right join user_activity t2 on t1.id=t2.activity_id where 1=1 and t2.user_id=? ";
		String sqlCount =" select count(1) from activity t1 right join user_activity t2 on t1.id=t2.activity_id where 1=1 and t2.user_id='"+reqMap.get("userId")+"'";
		
		if(null!=reqMap.get("activityId") && !"".equals(reqMap.get("activityId"))){
			sqlStr += " and t2.activity_id ='"+reqMap.get("activityId")+"' "; 
			sqlCount +=" and t2.activity_id ='"+reqMap.get("activityId")+"' "; 
		}
		
		// 获取当前我的活动总数
		int dataCount = jdbcTemplate.queryForInt(sqlCount);
		// 查询的分页参数
    	Map pageInfo = (Map) reqMap.get("pageInfo");
    	int curPage = (int) pageInfo.get("curPage");
    	int pageLimit = (int) pageInfo.get("pageLimit");
    	int startIndex = (curPage-1)*pageLimit;
    	sqlStr += " limit " + startIndex + "," + pageLimit;
    	
    	logger.info("sql日志输出:sqlStr===="+sqlStr);
    	logger.info("sql日志输出:sqlCount===="+sqlCount);
		
		List activityList =  jdbcTemplate.queryForList(sqlStr, new Object[]{reqMap.get("userId")});
		JSONView jsonView = new JSONView();
		jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
        jsonView.setReturnMsg("活动次数增加成功");
		jsonView.addAttribute("activityList", activityList);
		jsonView.addAttribute("dataCount", dataCount);
        logger.info("activityList=" + activityList);
        return jsonView;
	}
}
