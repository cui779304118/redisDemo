package com.redis.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisHashDemo {
	
	public static void main(String [] args){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context/applicationContext-redis.xml");
		RedisTemplate redisTemplate =(RedisTemplate) context.getBean(RedisTemplate.class);
		
		String key = "hash";
		Map<String,String> map = new HashMap<String,String>();
		map.put("f1","val1");
		map.put("f2","val2");
		
		//相当于hmset命令
		redisTemplate.opsForHash().putAll(key, map);
		//相当于hset命令
		redisTemplate.opsForHash().put(key, "f3", "6");
		printValueForhash(redisTemplate,key,"f3");
		
		//相当于hexists key filed 命令	
		boolean isExist = redisTemplate.opsForHash().hasKey(key, "f3");
		System.out.println(isExist);
		
		//相当于hgetall命令
		Map keyValMap = redisTemplate.opsForHash().entries(key);
	    //相当于hincrby命令
		redisTemplate.opsForHash().increment(key, "f3", 2);
		printValueForhash(redisTemplate, key, "f3");
		//相当于hincrebyfloat
		redisTemplate.opsForHash().increment(key, "f3", 0.88);
		printValueForhash(redisTemplate, key, "f3");
		//相当于hvals命令
		List valueList = redisTemplate.opsForHash().values(key);
		System.out.println(valueList);
		//相当于hkeys命令
		Set keySet = redisTemplate.opsForHash().keys(key);
		System.out.println(keySet);
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("f1");
		fieldList.add("f1");
		//相当于hmget命令
		List valueList2 = redisTemplate.opsForHash().multiGet(key, fieldList);
		System.out.println(valueList2);
		//相当于hsetnx命令
		boolean success = redisTemplate.opsForHash().putIfAbsent(key, "f4","val4" );
		System.out.println(success);
		//相当于hdel命令
		redisTemplate.opsForHash().delete(key, "f1","f2");
		System.out.println(redisTemplate.opsForHash().values(key));
		
		
		
	}

	private static void printValueForhash(RedisTemplate redisTemplate,
			String key, String field) {
		Object value = redisTemplate.opsForHash().get(key,field);
		System.out.println(value);
		
	}

}
