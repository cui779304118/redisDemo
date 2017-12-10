package com.redis.spring;

import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisSetDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context/applicationContext-redis.xml");
		RedisTemplate redisTemplate = (RedisTemplate) context.getBean(RedisTemplate.class);
		
		Set<String> set = null;
		
		redisTemplate.boundSetOps("set1").add("v1","v2","v3","v4","v5","v6");
		redisTemplate.boundSetOps("set2").add("v0","v2","v4","v6","v8");
		
		Long size1=redisTemplate.opsForSet().size("set1");
		System.out.println(size1);
		
		set=redisTemplate.opsForSet().difference("set1", "set2");
		System.out.println("差集："+set);
		
		set=redisTemplate.opsForSet().intersect("set1", "set2");
		System.out.println("交集："+set);
		
		set=redisTemplate.opsForSet().union("set1", "set2");
		System.out.println("并集："+set);
		
		boolean exists = redisTemplate.opsForSet().isMember("set1", "v1");
		System.out.println(exists);
		
		set=redisTemplate.opsForSet().members("set1");
		System.out.println("原始集合："+set);
		
		String val = (String) redisTemplate.opsForSet().pop("set1");
		String val0= (String) redisTemplate.opsForSet().randomMember("set1");
		System.out.println(val+","+val0);
		
		List<String> list = redisTemplate.opsForSet().randomMembers("set1", 2L);
		System.out.println(list);
		
		redisTemplate.opsForSet().remove("set1", "v1");
		
		redisTemplate.opsForSet().differenceAndStore("set1", "set2", "diff_set");
		redisTemplate.opsForSet().intersectAndStore("set1", "set2", "inter_set");
		redisTemplate.opsForSet().unionAndStore("set1", "set2", "union_set");
		
	}

}
