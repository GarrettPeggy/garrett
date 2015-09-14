/**
 * 
 */
package test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 
 * 基于AbstractTransactionalSpringContextTests的抽象测试类 
 *  
 * 继承此类，某个测试方法需要事物回滚时，直接在方法前加@Rollback(true)即可
 * @author WangGuanghua
 *
 */
@ContextConfiguration("../server-applicationContext.xml")
public abstract class AbstractServiceTransactionalTests extends AbstractTransactionalJUnit4SpringContextTests { 
 
   /** 
    * 无参构造函数 
    */  
   public AbstractServiceTransactionalTests() {  
       super();  
   } 
   
}
