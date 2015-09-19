package test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.campD.server.data.ActivityJsonServer;
/**
 * 
 * @author qrh
 *
 */
public class ActivityTestService extends AbstractServiceTransactionalTests {
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private ActivityJsonServer activityJsonServer;
	
	//reqMap:{categoryId：活动所属范畴id，actNum:活动人数，actCity：活动城市，actType:活动类型,requirement：活动需求，creator：活动提交人，
	//createTime：活动提交时间，status：活动状态}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Rollback(false)
	@Test
	public void testAdd(){
		Map reqMap = new HashMap();
		reqMap.put("categoryId", 1);
		reqMap.put("actNum", 22);
		reqMap.put("actCity", "北京");
		reqMap.put("actType", "1");
		reqMap.put("requirement", "场地足够大");
		reqMap.put("creator", "018ffd2c-adab-4131-8ef6-af00650ccee2");
		reqMap.put("status", 1);
		logger.info("添加活动需求结果是----> " + activityJsonServer.add(reqMap));  
	} 
	
	//reqMap:{categoryId：活动所属范畴id，actNum:活动人数，actCity：活动城市，requirement：活动需求，adress:活动地址，
	//sponsor：活动发起方，status：活动状态，show_image：活动展示的图片，title：活动标题，
	//subTitle：活动副标题，beginTime:开始时间，endTime：结束时间，publishTime：发布时间,id:活动Id}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Rollback(false)
	@Test
	public void testUpdate(){
		Map reqMap = new HashMap();
		reqMap.put("categoryId", 1);
		reqMap.put("actNum", 33);
		reqMap.put("actCity", "上海");
		reqMap.put("requirement", "梅塞德斯-奔驰文化中心");
		reqMap.put("adress", "上海市浦东区");
		reqMap.put("sponsor", "北京长夜公司");
		reqMap.put("status", 2);
		reqMap.put("show_image", "图片url地址");
		reqMap.put("title", "我爱图片");
		reqMap.put("subTitle", "我爱图片哎活动");
		reqMap.put("beginTime", new Date());
		reqMap.put("endTime", new Date());
		reqMap.put("publishTime", new Date());
		reqMap.put("id", "5beb7ba8-7b7d-4bf4-bb65-bf9ad3143aa6");
		logger.info("活动更新结果是----> " + activityJsonServer.update(reqMap));  
	}
	
	//reqMap:{id:活动id}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Rollback(false)
	@Test
	public void testUpdateClick(){
		Map reqMap = new HashMap();
		reqMap.put("id", "5beb7ba8-7b7d-4bf4-bb65-bf9ad3143aa6");
		logger.info("更新活动点击次数结果是----> " + activityJsonServer.updateClick(reqMap));  
	}
	
	//reqMap:{categoryId：活动所属范畴id，createTime：活动提交时间，status：活动状态}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetActivityList(){
		Map reqMap = new HashMap();
		//reqMap.put("categoryId", 1);
		//reqMap.put("createTime", new Date());
		reqMap.put("status", 2);
		logger.info("根据活动所属范畴id,活动提交时间，活动状态等信息查询活动结果是----> " + activityJsonServer.getActivityList(reqMap));  
		
	}
	
	//reqMap:{id：活动id}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetActivityById(){
		Map reqMap = new HashMap();
		reqMap.put("id", "5beb7ba8-7b7d-4bf4-bb65-bf9ad3143aa6");
		logger.info("根据Id查询活动详情结果是----> " + activityJsonServer.getActivityById(reqMap));  
	}
	
	//reqMap:{categoryId：活动id，userId：参与活动的人的id，enrollTime：报名时间}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Rollback(false)
	@Test
	public void testTakeAnActive(){
		Map reqMap = new HashMap();
		reqMap.put("categoryId", "5beb7ba8-7b7d-4bf4-bb65-bf9ad3143aa6");
		reqMap.put("userId", "018ffd2c-adab-4131-8ef6-af00650ccee2");
		reqMap.put("enrollTime", new Date());
		logger.info("用户参加活动结果是----> " + activityJsonServer.takeAnActive(reqMap));  
	}
	
	//reqMap:{userId：当前用户id}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetMyTakeAnActive(){
		Map reqMap = new HashMap();
		reqMap.put("userId", "018ffd2c-adab-4131-8ef6-af00650ccee2");
		logger.info("查询我的报名活动结果是----> " + activityJsonServer.getMyTakeAnActive(reqMap));
	}
	
	
}
