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
		String sqlStr = "insert into activity(id,creator_id,category_id,act_num,act_city,act_type,requirement,create_time,status) values(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("creator"), reqMap.get("categoryId"), reqMap.get("actNum"),reqMap.get("actCity"),reqMap.get("actType"),reqMap.get("requirement"),new Date(),reqMap.get("status")};
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
		String sqlStr = " update activity set category_id=?,act_num=?,act_city=?,act_type=?requirement=?,adress=?,sponsor=?,status=?,show_image=?,title=?,sub_title=?,begin_time=?,end_time=?,publish_time=? where id=? ";
        Object[] params = new Object[]{reqMap.get("categoryId"), reqMap.get("actNum"), reqMap.get("actCity"),reqMap.get("actType"),reqMap.get("requirement"),reqMap.get("adress"),reqMap.get("sponsor"),reqMap.get("status"),reqMap.get("show_image"),reqMap.get("title"),reqMap.get("subTitle"),reqMap.get("beginTime"),reqMap.get("endTime"),reqMap.get("publishTime"),reqMap.get("id")};
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
	 * @param reqMap:{categoryId：活动所属范畴id，createTime：活动提交时间，status：活动状态}
	 * @return
	 */
	public Map getActivityList(Map reqMap){
		logger.info("reqMap="+reqMap);
		String sqlStr = " select id,creator_id,category_id,act_num,adress,sponsor,act_city,act_type,requirement,assistance,show_image,title,sub_title,begin_time,end_time,click_num,create_time,publish_time,status from activity where 1=1 ";
		if(null!=reqMap.get("categoryId") && !"".equals(reqMap.get("categoryId"))){
			sqlStr+=" and creator_id like '%"+reqMap.get("categoryId")+"%' ";
		}
		if(null!=reqMap.get("createTime") && !"".equals(reqMap.get("createTime"))){
			sqlStr+=" and create_time like '%"+reqMap.get("createTime")+"%' ";
		}
		if(null!=reqMap.get("status") && !"".equals(reqMap.get("status"))){
			sqlStr+=" and status like '%"+reqMap.get("status")+"%' ";
		}
		List activityList =  jdbcTemplate.queryForList(sqlStr, new Object[]{});//多条信息
		JSONView jsonView = new JSONView();
		jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
        jsonView.setReturnMsg("活动次数增加成功");
		jsonView.addAttribute("activityList", activityList);
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
		String sqlStr = " select id,creator_id,category_id,act_num,adress,sponsor,act_city,act_type,requirement,assistance,show_image,title,sub_title,begin_time,end_time,click_num,create_time,publish_time,status from activity where 1=1 and id=? ";
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
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("userId"), reqMap.get("categoryId"), reqMap.get("enrollTime")};
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
		String sqlStr = " select t1.id,t1.creator_id,t1.category_id,t1.act_num,t1.adress,t1.sponsor,t1.act_city,t1.act_type,t1.requirement,t1.assistance,t1.show_image,t1.title,t1.sub_title,t1.begin_time,t1.end_time,t1.click_num,t1.create_time,t1.publish_time,t1.status from activity t1 right join user_activity t2 on t1.id=t2.activity_id where 1=1 and t2.user_id=? ";
		List myTakeAnActiveList =  jdbcTemplate.queryForList(sqlStr, new Object[]{reqMap.get("userId")});
		JSONView jsonView = new JSONView();
		jsonView.setReturnCode(JSONView.RETURN_SUCCESS_CODE);
        jsonView.setReturnMsg("活动次数增加成功");
		jsonView.addAttribute("myTakeAnActiveList", myTakeAnActiveList);
        logger.info("myTakeAnActiveList=" + myTakeAnActiveList);
        return jsonView;
	}
}
