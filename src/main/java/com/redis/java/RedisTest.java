package com.redis.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;



public class RedisTest {

	
	private Jedis jedis = RedisUtils.getJedis();
		
	/**
	 * jedis操作set
	 */
	
	//@Test
	public void testSet(){
		String key ="key:set";
		jedis.sadd(key, "cuiwei");
		jedis.sadd(key, "liewer");
		jedis.sadd(key, "cuiwei");
		jedis.sadd(key, "dgg");
		jedis.sadd(key, "sdgdf");
		
		jedis.srem(key, "cuiwei");
		
		Set<String> set = jedis.smembers(key);
		
		System.out.println(set);
		System.out.println(jedis.sismember(key, "dgg"));
		System.out.println(jedis.srandmember(key));
		System.out.println(jedis.scard(key));
		
	}
	
//	@Test
	public void testList(){
		String key = "key:list";
		
		jedis.del(key);
		for(int i=0;i<=3;i++){
			jedis.lpush(key, "value" + i);
		}
		List<String> list1 = jedis.lrange(key, 0, -1);
		System.out.println(list1);
		
		for(int i=1;i<=3;i++){
			jedis.rpush(key, "value" + i);
		}
		List<String> list2 = jedis.lrange(key, 0, -1);
		System.out.println(list2);
	}
	
//	@Test
	public void testHash(){
		String key = "key:hash";
		
		Map<String,String> map = new HashMap<String,String>(){
			{
				put("name","cuiwei");
				put("age","22");
				put("qq","843924234");
			}
		};
		
		jedis.hmset(key,map);
		
		List<String> rsmap = jedis.hmget(key, "name" , "age" , "qq");
		System.out.println(rsmap);
		
		jedis.hdel(key, "name");
		
		System.out.println(jedis.hmget(key, "name"));
		System.out.println(jedis.hlen(key));
		System.out.println(jedis.exists("name"));
		System.out.println(jedis.hkeys(key));
		System.out.println(jedis.hvals(key));
		
		List<String> valueList = jedis.hvals(key);
		Set<String> keyList = jedis.hkeys(key);
		
		System.out.println(valueList);
		System.out.println(keyList);
		
	}
	
//	@Test
	public void testString(){
		String key = "key:string";
		
		jedis.set(key,"cuiwei");
		System.out.println(jedis.get(key));
		
		jedis.append(key, " is my lover");
		System.out.println(jedis.get(key));
		
		jedis.del(key);
		System.out.println(jedis.get(key));
		
		jedis.mset("name","cuiwei","age","23","qq","779304118");
		jedis.incr("age");
		System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
		
	}
	
	@Test
	public void testList2(){
		String key = "key:list2";
		
		jedis.del(key);
		
		jedis.rpush(key,"1");
		jedis.rpush(key, "6");
		jedis.lpush(key, "3");
		jedis.lpush(key, "9");
		jedis.lpush(key, "wang");
		
		System.out.println(jedis.lrange(key, 0, -1));
		try {
			List<String> listSort = jedis.sort(key);
			System.out.println(listSort);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("xfas");
	}
	

}
