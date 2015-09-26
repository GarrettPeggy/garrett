/**
 * 
 */
package test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.campD.server.data.UserJsonServer;

/**
 * @author Administrator
 *
 */
public class UserTestService extends AbstractServiceTransactionalTests {

	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired  
    private UserJsonServer userJsonServer;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Rollback(false)
	@Test  
    public void testRegister() {  
        
		// 本测试用例是连接test数据库的，并不是真的数据库
		Map reqMap = new HashMap();
		reqMap.put("userName", "王光华1");
		reqMap.put("mdn", "13400000000");
		reqMap.put("roleId", "f3f0228d-c7d4-4f39-96f8-3cb4969787ae");// 这个测试的id是普通用户的id，不能随便去uuid测试。
		logger.info("注册结果是-> " + userJsonServer.register(reqMap));  
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test  
    public void testFindUserByMdn() {  
        
		// 本测试用例是连接test数据库的，并不是真的数据库
		Map reqMap = new HashMap();
		reqMap.put("mdn", "1341000000");
		logger.info("查找到的当前用户是-> " + userJsonServer.findUserByMdn(reqMap));  
		
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test  
    public void testFindUserList() {  
        
		// 本测试用例是连接test数据库的，并不是真的数据库
		Map reqMap = new HashMap();
		reqMap.put("mdn", "13400000000");
		reqMap.put("userName", "王光华");
		reqMap.put("beginRegTime", "2015-09-01");
		reqMap.put("endRegTime", "2015-09-21");
		reqMap.put("beginLoginTime", "2015-09-01");
		reqMap.put("endLoginTime", "2015-09-21");
		
		Map pageInfo = new HashMap();
		pageInfo.put("curPage", "1");
		pageInfo.put("pageLimit", "10");
		reqMap.put("pageInfo", pageInfo);
		logger.info("查找到的当前用户列表是-> " + userJsonServer.findUserList(reqMap));  
		
    }
}
