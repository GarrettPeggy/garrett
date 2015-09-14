/**
 * 
 */
package test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.campD.server.data.DemoJsonServer;

/**
 * 测试junit4
 * @author Administrator
 *
 */

public class DemoTestService extends AbstractServiceTransactionalTests {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired  
    private DemoJsonServer demoJsonServer;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test  
    public void testFindByUserName() {  
        
		// 本测试用例是连接test数据库的，并不是真的数据库
		Map reqMap = new HashMap();
		reqMap.put("userName", "王光华");
		logger.info("查找到的用户是-> " + demoJsonServer.findUserByUserName(reqMap));  
    } 

}
