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
 * 礼品接口
 * @author Garrett
 *
 */
@Repository
public class GiftJsonServer {

	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 添加礼品
	 * @param reqMap:{}
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map add(Map reqMap){
		logger.info("reqMap="+reqMap);
		String sqlStr = "insert into gift(id,creator_id,create_time,name,provider_name,provider_province,provider_city,provider_area,provider_adress,main_business,contactor,contact,form,work_for,work_for_province,work_for_city,work_for_area,show_image,description,level) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        Object[] params = new Object[]{UUID.randomUUID().toString(), reqMap.get("creatorId"), new Date(), reqMap.get("name"),reqMap.get("providerName"),reqMap.get("providerProvince"), reqMap.get("providerCity"),reqMap.get("providerArea"),reqMap.get("providerAdress"),reqMap.get("mainBusiness"),reqMap.get("contactor"),reqMap.get("contact"),reqMap.get("form"),reqMap.get("workFor"),reqMap.get("workForProvince"),reqMap.get("workForCity"),reqMap.get("workForArea"),reqMap.get("showImage"),reqMap.get("description"),reqMap.get("level")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("添加礼品失败，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnMsg("礼品添加成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("后台场地发布成功->updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	
	/**
	 * 更新礼品
	 * @param reqMap:{}
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map update(Map reqMap){
		
		logger.info("reqMap="+reqMap);
		
		// 添加场地到数据库
		String sqlStr = "update gift set create_time=?, name=?, provider_name=?, provider_province=?, provider_city=?, provider_area=?, provider_adress=?, main_business=?, contactor=?, contact=?, form=?, work_for=?, work_for_province=?, work_for_city=?, work_for_area=?,show_image=?,description=? where id=?";
        Object[] params = new Object[]{new Date(), reqMap.get("name"),reqMap.get("providerName"),reqMap.get("providerProvince"), reqMap.get("providerCity"),reqMap.get("providerArea"),reqMap.get("providerAdress"),reqMap.get("mainBusiness"),reqMap.get("contactor"),reqMap.get("contact"),reqMap.get("form"),reqMap.get("workFor"),reqMap.get("workForProvince"),reqMap.get("workForCity"),reqMap.get("workForArea"),reqMap.get("showImage"),reqMap.get("description"),reqMap.get("id")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("后台更新礼品失败，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnMsg("礼品更新成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("后台场地更新成功->updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	
	/**
	 * 更新礼品级别
	 * @param {id：场地id，level： 礼品级别}
	 * spaceLevel:场地级别(0:普通礼品，1：精美礼品)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map updateLevel(Map reqMap){
		
		logger.info("reqMap="+reqMap);
		
		// 添加场地到数据库
		String sqlStr = " update gift set level=? where id=?";
        Object[] params = new Object[]{reqMap.get("level"),reqMap.get("id")};
        int updateLineCount = jdbcTemplate.update(sqlStr, params);
        
        JSONView jsonView = new JSONView();
        if(updateLineCount <= 0){
        	jsonView.setFail();
			jsonView.setReturnErrorMsg();
			logger.info("后台更新礼品失败，此时传入参数为->params="+params.toString());
			return jsonView;
        }
        
        jsonView.setReturnMsg("礼品级别更新成功");
        jsonView.addAttribute("updateLineCount", updateLineCount);
        logger.info("后台场地级别更新成功,\\(^o^)/。。。，updateLineCount="+updateLineCount);
        
        return jsonView;
	}
	
	/**
	 * 查询礼品列表信息
	 * @param reqMap:{}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public Map getGiftList(Map reqMap){
		
		logger.info("reqMap="+reqMap);
		
		String sqlStr = "select id,create_time,name,provider_name,provider_province,provider_city,provider_area,provider_adress,main_business,contactor,contact,form,work_for,work_for_province,work_for_city,work_for_area,show_image,description,level from gift where 1=1 ";
		String sqlCount ="select count(1) from gift where 1=1 ";
		
		Object name = reqMap.get("name");//名称
		if(null!=name && !"".equals(name)){
			sqlStr+=" and name like '%"+name+"%' ";
			sqlCount+=" and name like '%"+name+"%' ";
		}
		
		Object providerName = reqMap.get("providerName");//礼品提供方名称
		if(null!=providerName && !"".equals(providerName)){
			sqlStr+=" and provider_name like '%"+providerName+"%' ";
			sqlCount+=" and provider_name like '%"+providerName+"%' ";
		}

		Object contactor = reqMap.get("contactor");//联系人
		if(null!=contactor && !"".equals(contactor)){
			sqlStr+=" and contactor like '%"+contactor+"%' ";
			sqlCount+=" and contactor like '%"+contactor+"%' ";
		}

		Object contact = reqMap.get("contact");//联系方式
		if(null!=contact && !"".equals(contact)){
			sqlStr+=" and contact ="+contact+" ";
			sqlCount+=" and contact ="+contact+" ";
		}
		
		Object providerProvince = reqMap.get("providerProvince");//提供方省份
		if(null!=providerProvince && !"".equals(providerProvince)){
			sqlStr+=" and provider_province='"+providerProvince+"' ";
			sqlCount+=" and provider_province='"+providerProvince+"' ";
		}
		
		Object providerCity = reqMap.get("providerCity");//提供方区域
		if(null!=providerCity && !"".equals(providerCity)){
			sqlStr+=" and provider_city='"+providerCity+"' ";
			sqlCount+=" and provider_city='"+providerCity+"' ";
		}

		Object providerArea = reqMap.get("providerArea");//提供方地级市
		if(null!=providerArea && !"".equals(providerArea)){
			sqlStr+=" and provider_area='"+providerArea+"' ";
			sqlCount+=" and provider_area='"+providerArea+"' ";
		}

		Object workFor = reqMap.get("workFor");//该字段是以逗号隔开的，适用活动
		if(null!=workFor && !"".equals(workFor)){
			sqlStr+=" and FIND_IN_SET("+workFor+", work_for) ";
			sqlCount+=" and FIND_IN_SET("+workFor+", work_for) ";
		}
		
		Object workForProvince = reqMap.get("workForProvince");//礼品适用省份
		if(null!=workForProvince && !"".equals(workForProvince)){
			sqlStr+=" and work_for_province='"+workForProvince+"' ";
			sqlCount+=" and work_for_province='"+workForProvince+"' ";
		}
		
		Object workForCity = reqMap.get("workForCity");//礼品适用区域
		if(null!=workForCity && !"".equals(workForCity)){
			sqlStr+=" and FIND_IN_SET('"+workForCity+"', work_for_city) ";
			sqlCount+=" and FIND_IN_SET('"+workForCity+"', work_for_city) ";
		}

		Object workForArea = reqMap.get("workForArea");//礼品适用地级市
		if(null!=workForArea && !"".equals(workForArea)){
			sqlStr+=" and work_for_area='"+workForArea+"' ";
			sqlCount+=" and work_for_area='"+workForArea+"' ";
		}

		Object mainBusiness = reqMap.get("mainBusiness");//礼品主营业务
		if(null!=mainBusiness && !"".equals(mainBusiness)){
			sqlStr+=" and main_business="+mainBusiness;
			sqlCount+=" and main_business="+mainBusiness;
		}
		
		Object form = reqMap.get("form");//礼品形态
		if(null!=form && !"".equals(form)){
			sqlStr+=" and form="+form;
			sqlCount+=" and form="+form;
		}

		Object beginCreateTime = reqMap.get("beginCreateTime");//开始时间
		Object endCreateTime = reqMap.get("endCreateTime");//结束时间
		if(null!=beginCreateTime && !"".equals(beginCreateTime)){
			sqlStr+=" and create_time>='"+beginCreateTime + " 00:00:00'";
			sqlCount+=" and create_time>='"+beginCreateTime + " 00:00:00'";
		}
		if(null!=endCreateTime && !"".equals(endCreateTime)){
			sqlStr+=" and create_time<'"+endCreateTime + " 23:59:59'";
			sqlCount+=" and create_time<'"+endCreateTime + " 23:59:59'";
		}
		
		Object level = reqMap.get("level");//礼品特征
		if(null!=level && !"".equals(level)){
			sqlStr+=" and level="+level+" ";
			sqlCount+=" and level="+level+" ";
		}
		
		// 获取当前场地总数
		int dataCount = jdbcTemplate.queryForInt(sqlCount);
		
		Object isRand = reqMap.get("isRand");//是否随机取数据
		if(null!=isRand && !"".equals(isRand)){
			// 首页精美礼品随机取
			sqlStr += " ORDER BY RAND() ";
		} else{
			// 默认按照发布时间降序排列
			sqlStr += " ORDER BY create_time DESC ";
		}
		
		// 查询的分页参数
    	Map pageInfo = (Map) reqMap.get("pageInfo");
    	int curPage = (int) pageInfo.get("curPage");
    	int pageLimit = (int) pageInfo.get("pageLimit");
    	int startIndex = (curPage-1)*pageLimit;
    	sqlStr += " limit " + startIndex + "," + pageLimit;
    	
    	logger.info("sql日志输出:sqlStr===="+sqlStr);
    	logger.info("sql日志输出:sqlCount===="+sqlCount);
		
		List giftList = jdbcTemplate.queryForList(sqlStr, new Object[]{});
    	JSONView jsonView = new JSONView();
    	jsonView.setSuccess();
    	jsonView.setReturnMsg("礼品列表查询成功");
        jsonView.addAttribute("giftList", giftList);
        jsonView.addAttribute("dataCount", dataCount);
        
        logger.info("礼品列表信息查询->giftList="+giftList);
        
        return jsonView;
	}
	
	/**
	 * 根据Id查询礼品信息
	 * @param reqMap:{id:场地id}
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map getGiftById(Map reqMap){
		logger.info("reqMap="+reqMap);
		
		String sqlStr = "select id,create_time,name,provider_name,provider_province,provider_city,provider_area,provider_adress,main_business,contactor,contact,form,work_for,work_for_province,work_for_city,work_for_area,show_image,description,level from gift where id=? ";
		Map giftMap = jdbcTemplate.queryForMap(sqlStr, new Object[]{reqMap.get("id")});
		
		JSONView jsonView = new JSONView();
		jsonView.setReturnMsg("礼品添加成功");
		jsonView.addAttribute("giftMap", giftMap);
		logger.info("礼品信息查询->" + giftMap);
		
		return jsonView;
	}
}
