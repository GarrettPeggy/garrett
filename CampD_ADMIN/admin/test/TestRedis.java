/**
 * 
 */
package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * Redis 测试类
 * @author Garrett
 *
 */
public class TestRedis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 连接到Redis
	    Jedis jedis = new Jedis("112.124.63.41", 6379);
	    jedis.auth("CDying123");
	    System.out.println("Server is running: "+jedis.ping());
	    
//	    // 测试字符串类型
//	    testString(jedis);
//	    // 测试列表类型
//	    testList(jedis);
//	    // keys * 测试
//	    testKeys(jedis);
//	    // 测试set
//	    testSet(jedis);
//	    // 测试map
//	    testMap(jedis);
//	    // 测试排序
//	    testSort(jedis);
	    // 测试Lue脚本程序
	    testLuaScript(jedis);
	    
	}
	
	public static void testString(Jedis jedis){
		jedis.set("foo", "王光华");
	    System.out.println("foo的值为: "+jedis.get("foo"));
	    
	    jedis.append("name", " is my lover"); //拼接
        System.out.println(jedis.get("name")); 
        
        jedis.del("name");  //删除某个键
        System.out.println(jedis.get("name"));
        //设置多个键值对
        jedis.mset("name","liuling","age","23","qq","476777XXX");
        jedis.incr("age"); //进行加1操作
        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
	}

	public static void testList(Jedis jedis){
		jedis.lpush("testList", "wang");
	    jedis.lpush("testList", "guang");
	    jedis.lpush("testList", "hua");
	    jedis.lpush("testList", "running");
	    List<String> testList = jedis.lrange("testList", 0, -1);
	    for (int i = 0; i < testList.size(); i++) {
	    	System.out.println("testList["+i+"]: "+testList.get(i));
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void testKeys(Jedis jedis){
		Set<String> keySet = jedis.keys("*");
	    Iterator iterator = keySet.iterator();
	    while(iterator.hasNext()){
	    	System.out.println(iterator.next());
		}
	}
	
	public static void testMap(Jedis jedis) {
        //-----添加数据----------  
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");
        jedis.hmset("user",map);
        //取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List  
        //第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数  
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);  
  
        //删除map中的某个键值  
        jedis.hdel("user","age");
        System.out.println(jedis.hmget("user", "age")); //因为删除了，所以返回的是null  
        System.out.println(jedis.hlen("user")); //返回key为user的键中存放的值的个数2 
        System.out.println(jedis.exists("user"));//是否存在key为user的记录 返回true  
        System.out.println(jedis.hkeys("user"));//返回map对象中的所有key  
        System.out.println(jedis.hvals("user"));//返回map对象中的所有value 
  
        Iterator<String> iter=jedis.hkeys("user").iterator();  
        while (iter.hasNext()){  
            String key = iter.next();  
            System.out.println(key+":"+jedis.hmget("user",key));  
        }  
    }
	
	public static void testSet(Jedis jedis){  
        //添加  
        jedis.sadd("user","liuling");  
        jedis.sadd("user","xinxin");  
        jedis.sadd("user","ling");  
        jedis.sadd("user","zhangxinxin");
        jedis.sadd("user","who");  
        //移除noname  
        jedis.srem("user","who");  
        System.out.println(jedis.smembers("user"));//获取所有加入的value  
        System.out.println(jedis.sismember("user", "who"));//判断 who 是否是user集合的元素  
        System.out.println(jedis.srandmember("user"));  
        System.out.println(jedis.scard("user"));//返回集合的元素个数  
    } 
	
	public static void testSort(Jedis jedis) {  
        //jedis 排序  
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）  
        jedis.del("a");//先清除数据，再加入数据进行测试  
        jedis.rpush("a", "1");  
        jedis.lpush("a","6");  
        jedis.lpush("a","3");  
        jedis.lpush("a","9");  
        System.out.println(jedis.lrange("a",0,-1));// [9, 3, 6, 1]  
        System.out.println(jedis.sort("a")); //[1, 3, 6, 9]  //输入排序后结果  
        System.out.println(jedis.lrange("a",0,-1));  
    } 
	
	public static void testLuaScript(Jedis jedis){
		jedis.eval("return redis.call('SET', KEYS[1],ARGV[1])", 1, "foo", "garrett");
		// 只有在执行eval之后，执行evalsha才有效
		//jedis.evalsha("foo", 1, "foo", "hua");
	}
}
