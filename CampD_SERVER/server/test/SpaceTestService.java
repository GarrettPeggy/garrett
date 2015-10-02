package test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.campD.server.data.SpaceJsonServer;

public class SpaceTestService extends AbstractServiceTransactionalTests {
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private SpaceJsonServer spaceJsonServer;
	
	//reqMap:{creatorId：创建者id，createTime：创建时间，name：场地名称，adress：场地地址，traggic：交通状况，
	//workFor：使用哪些活动，capacity：场地容量，spaceType：场地类型，contactor：场地联系人，
	//cost：花费，contact：联系方式，showImages：场地展示图片，description：场地描述,spaceLevel:场地级别}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Rollback(false)
	@Test
	public void testAdd(){
		Map reqMap = new HashMap();
		reqMap.put("creatorId", "018ffd2c-adab-4131-8ef6-af00650ccee2");
		reqMap.put("name", "好山好水情长在");
		reqMap.put("adress", "上海浦东机场");
		reqMap.put("traggic", "高楼林立，四通八达");
		reqMap.put("workFor", "聚餐，同学聚会");
		reqMap.put("capacity", 221);
		reqMap.put("spaceType", 0);
		reqMap.put("contactor", "宋江");
		reqMap.put("cost", 22221.22d);
		reqMap.put("contact", "13122222222");
		reqMap.put("showImages", "图片url地址");
		reqMap.put("description", "高端大气");
		reqMap.put("spaceLevel", 1);
		logger.info("后台场地发布结果是----> " + spaceJsonServer.add(reqMap));  
	}
	
	//reqMap:{name：场地名称，adress：场地地址，workFor：使用哪些活动(是就是范畴id)，capacity：场地容量，cost：花费}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetSpaceInfoList(){
		Map reqMap = new HashMap();
		//reqMap.put("name", "山");
		//reqMap.put("adress", "金茂");
		//reqMap.put("workFor", "那些红");
		//reqMap.put("capacity", 22);
		reqMap.put("cost", 2);
		logger.info("查询场地列表信息结果是----> " + spaceJsonServer.getSpaceInfoList(reqMap));  
	}
	
	//reqMap:{id:场地id}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetSpaceInfoById(){
		Map reqMap = new HashMap();
		reqMap.put("id", "755e2a57-c955-4714-972e-4f81a04cc993");
		logger.info("查询场地详情结果是----> " + spaceJsonServer.getSpaceInfoById(reqMap));  
	}
}
