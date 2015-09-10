/**
 * 
 */
package com.campD.portal.interceptor.ehcache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 主要用来拦截以get和find开头的方法（用于缓存结果）
 * @author Administrator
 *
 */
public class MethodCacheInterceptor implements MethodInterceptor, InitializingBean {

	private static final Logger logger = Logger.getLogger(MethodCacheInterceptor.class);  
  
    private Cache cache;  
  
    public void setCache(Cache cache) {  
        this.cache = cache;  
    }  
  
    public MethodCacheInterceptor() {  
        super();  
    }  
  
    /** 
     * 拦截Service/DAO 的方法，并查找该结果是否存在，如果存在就返回cache 中的值， 31 * 
     * 否则，返回数据库查询结果，并将查询结果放入cache 32 
     */  
    public Object invoke(MethodInvocation invocation) throws Throwable {  
        String targetName = invocation.getThis().getClass().getName();  
        String methodName = invocation.getMethod().getName();  
        Object[] arguments = invocation.getArguments();  
        Object result;  
  
        logger.debug("Find object from cache is " + cache.getName());  
  
        String cacheKey = getCacheKey(targetName, methodName, arguments);  
        Element element = cache.get(cacheKey);  
  
        if (element == null) {  
            logger.debug("Hold up method , Get method result and create cache........!");  
            result = invocation.proceed();  
            element = new Element(cacheKey, (Serializable) result);  
            logger.debug("-----非缓存中查找，查找后放入缓存----------");  
            cache.put(element);  
        }else{  
        	logger.debug("----缓存中查找----");  
        }  
        return element.getObjectValue(); // 此处原版使用的是“getValue”方法 
    }  
      
  
    /** 
     * 获得cache key 的方法，cache key 是Cache 中一个Element 的唯一标识 55 * cache key 
     * 包括包名+类名+方法名，如 com.co.cache.service.UserServiceImpl.getAllUser 56 
     */  
    private String getCacheKey(String targetName, String methodName,  
            Object[] arguments) {  
        StringBuffer sb = new StringBuffer();  
        sb.append(targetName).append(".").append(methodName);  
        if ((arguments != null) && (arguments.length != 0)) {  
            for (int i = 0; i < arguments.length; i++) {  
                sb.append(".").append(arguments[i]);  
            }  
        }  
        return sb.toString();  
    }  
  
    /** 
     * implement InitializingBean，检查cache 是否为空 70 
     */  
    public void afterPropertiesSet() throws Exception {  
        Assert.notNull(cache,  
                "Need a cache. Please use setCache(Cache) create it.");  
    }

}
