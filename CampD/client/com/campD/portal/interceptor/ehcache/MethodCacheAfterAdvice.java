/**
 * 
 */
package com.campD.portal.interceptor.ehcache;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.ehcache.Cache;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 
 * @author Administrator
 *
 */
public class MethodCacheAfterAdvice implements AfterReturningAdvice, InitializingBean {

	private static final Logger logger = Logger.getLogger(MethodCacheAfterAdvice.class); 
	
	private Cache cache;  
	  
    public void setCache(Cache cache) {  
        this.cache = cache;  
    }  
  
    public MethodCacheAfterAdvice() {  
        super();  
    }  
  
    @SuppressWarnings("rawtypes")
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {  
    	
        String className = arg3.getClass().getName();  
        List list = cache.getKeys();  
        for (int i = 0; i < list.size(); i++) {  
            String cacheKey = String.valueOf(list.get(i));  
            if (cacheKey.startsWith(className)) {  
                cache.remove(cacheKey);  
                logger.debug("------清除缓存----" + cacheKey); 
                logger.debug("remove cache " + cacheKey);  
            }  
        }  
    }  
  
    public void afterPropertiesSet() throws Exception {  
        Assert.notNull(cache, "Need a cache. Please use setCache(Cache) create it.");  
    }  

}
