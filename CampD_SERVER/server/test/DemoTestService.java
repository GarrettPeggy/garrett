/**
 * 
 */
package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.campD.server.data.DemoJsonServer;

/**
 * 测试junit4
 * @author Administrator
 *
 */

public class DemoTestService extends AbstractServiceTransactionalTests {
	
	@Autowired  
    private DemoJsonServer demoJsonServer;

	@Override
	String[] getOtherConfigs() {
		
		return new String[] { applicationContextFile }; 
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test  
    public void testFindByUserName() {  
        
		Map reqMap = new HashMap();
		reqMap.put("userName", "王光华");
        System.out.println("查找到的用户是-> " + demoJsonServer.findUserByUserName(reqMap));  
    } 

}
