package com.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisStringDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context/applicationContext-redis.xml");
		RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
		
		redisTemplate.opsForValue().set("key1", "value1");
		redisTemplate.opsForValue().set("key2", "value2");
		
		String value1 = (String) redisTemplate.opsForValue().get("key1");
		System.out.println(value1);
		redisTemplate.delete("key1");
		Long length = redisTemplate.opsForValue().size("key2");
		System.out.println(length);
		
		String oldValue2 = (String) redisTemplate.opsForValue().getAndSet("key2", "new_value2");
		System.out.println(oldValue2);
		
		String rangeValue2= redisTemplate.opsForValue().get("key2", 0, 2);
		System.out.println(rangeValue2);
		
		int newLen = redisTemplate.opsForValue().append("key2", "_app");
		System.out.println(newLen);
		
		String appendValue2 = (String) redisTemplate.opsForValue().get("key2");
		System.out.println(appendValue2);

	}

}
