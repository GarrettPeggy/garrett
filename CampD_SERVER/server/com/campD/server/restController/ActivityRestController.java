package com.campD.server.restController;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.campD.server.data.ActivityJsonServer;

/**
 * 
 * @author qrh
 *
 */
@RestController
@RequestMapping(value="/activity")
public class ActivityRestController extends BaseRestController {
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private ActivityJsonServer activityJsonServer;
	
	/**
	 * 添加活动需求
	 * @param reqMap:{categoryId：活动所属范畴id，actNum:活动人数，actCity：活动城市，actType:活动类型,requirement：活动需求，creator：活动提交人，createTime：活动提交时间，status：活动状态}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/add", method=RequestMethod.POST)
    @ResponseBody
	public Map add(HttpServletRequest request){
		Map reqMap = bindParamToMap(request);
    	Map returnMap = activityJsonServer.add(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 活动更新
	 * @param reqMap:{categoryId：活动所属范畴id，actNum:活动人数，actCity：活动城市，requirement：活动需求，adress:活动地址，sponsor：活动发起方，status：活动状态，show_image：活动展示的图片，title：活动标题，subTitle：活动副标题，beginTime:开始时间，endTime：结束时间，publishTime：发布时间,id:活动Id}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/update", method=RequestMethod.POST)
    @ResponseBody
	public Map update(HttpServletRequest request){
		Map reqMap = bindParamToMap(request);
    	Map returnMap = activityJsonServer.update(reqMap);
    	
        return returnMap;
	}
	
	
	/**
	 * 更新活动点击次数
	 * @param reqMap:{id:活动id}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/updateClick", method=RequestMethod.POST)
    @ResponseBody
	public Map updateClick(HttpServletRequest request){
		Map reqMap = bindParamToMap(request);
    	Map returnMap = activityJsonServer.updateClick(reqMap);
    	
        return returnMap;
	}
	
	
	/**
	 * 根据活动所属范畴id,活动提交时间，活动状态等信息查询活动
	 * @param reqMap:{categoryId：活动所属范畴id，createTime：活动提交时间，status：活动状态}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/getActivityList", method=RequestMethod.POST)
    @ResponseBody
	public Map getActivityList(HttpServletRequest request){
		Map reqMap = bindParamToMap(request);
    	Map returnMap = activityJsonServer.getActivityList(reqMap);
    	
        return returnMap;
	}
	
	
	/**
	 * 根据Id查询活动详情
	 * @param reqMap:{id：活动id}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/getActivityById", method=RequestMethod.POST)
    @ResponseBody
	public Map getActivityById(HttpServletRequest request){
		Map reqMap = bindParamToMap(request);
    	Map returnMap = activityJsonServer.getActivityById(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 用户参加活动
	 * @param reqMap:{categoryId：活动id，userId：参与活动的人的id，enrollTime：报名时间}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/takeAnActive", method=RequestMethod.POST)
    @ResponseBody
	public Map takeAnActive(HttpServletRequest request){
		Map reqMap = bindParamToMap(request);
    	Map returnMap = activityJsonServer.takeAnActive(reqMap);
    	
        return returnMap;
	}
	
	/**
	 * 查询我的报名活动
	 * @param reqMap:{userId：当前用户id}
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	@RequestMapping(value="/getMyTakeAnActive", method=RequestMethod.POST)
    @ResponseBody
	public Map getMyTakeAnActive(HttpServletRequest request){
		Map reqMap = bindParamToMap(request);
    	Map returnMap = activityJsonServer.getMyTakeAnActive(reqMap);
    	
        return returnMap;
	}
}
