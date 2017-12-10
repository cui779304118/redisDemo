package com.redis.spring;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisExpire {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext-redis.xml");
		RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
		redisTemplate.opsForValue().set("key1", "cuiwei");
		String keyValue = (String) redisTemplate.opsForValue().get("key1");
		Long expSecond = redisTemplate.getExpire("key1");
		System.err.println(expSecond);
		boolean b =false;
		b = redisTemplate.expire("key1",120L,TimeUnit.SECONDS);
		b = redisTemplate.persist("key1");
		Long l= 0L;
		l = redisTemplate.getExpire("key1");
		Long now = System.currentTimeMillis();
		Date date = new Date();
		date.setTime(now + 120000);
		redisTemplate.expireAt("key1", date);
		Long l2 = redisTemplate.getExpire("key1");
		System.err.println(l2);
	}

}
