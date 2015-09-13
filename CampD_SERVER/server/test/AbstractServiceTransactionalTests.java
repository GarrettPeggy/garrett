/**
 * 
 */
package test;

import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 
 * 基于AbstractTransactionalSpringContextTests的抽象测试类 
 *  
 * 继承此类，某个测试方法需要事物回滚时，直接在方法前加@Rollback(true)即可
 * @author WangGuanghua
 *
 */
public abstract class AbstractServiceTransactionalTests extends AbstractTransactionalJUnit4SpringContextTests {

	// 项目classpath路径  
    String applicationContextFile="classpath:server-applicationContext.xml";  
 
   /** 
    * 无参构造函数 
    */  
   public AbstractServiceTransactionalTests() {  
       super();  
   } 
 
   /** 
    * 需要加载的配置文件地址列表 
    * @return new String[] { applicationContextFile }; 
    */  
   abstract String[] getOtherConfigs();  
 
   /** 
    * 覆盖的获取配置文件地址的方法 
    */  
   protected String[] getConfigLocations() {  
       String[] otherConfigs = getOtherConfigs();  
       // 所有配置文件列表  
       String[] configFiles = new String[otherConfigs.length + 1];  
       configFiles[0] = applicationContextFile;  
 
       /** 
        * public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)  
        * 源数组中位置在 srcPos到srcPos+length-1 之间的组件被分 
        * 别复制到目标数组中的 destPos 到 destPos+length-1 位置。 
        */  
       System.arraycopy(otherConfigs, 0, configFiles, 1, otherConfigs.length);  
       return configFiles;  
   }
   
}
