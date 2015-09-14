/**
 * 
 */
package test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.campD.server.data.RoleJsonServer;

/**
 * @author Administrator
 *
 */
public class RoleTestService extends AbstractServiceTransactionalTests {
	
	protected Logger logger = Logger.getLogger(getClass());
	
	@Autowired  
    private RoleJsonServer roleJsonServer;

	@Test  
    public void testFindRoles() {  
        
		// 本测试用例是连接test数据库的，并不是真的数据库
		logger.info("当前系统中所有的角色是-> " + roleJsonServer.findRoles());  
    } 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test  
    public void testFindByRoleName() {  
        
		// 本测试用例是连接test数据库的，并不是真的数据库
		Map reqMap = new HashMap();
		reqMap.put("roleName", "普通用户");
		logger.info("查找到的角色是-> " + roleJsonServer.findRoleByName(reqMap));  
    }
}
