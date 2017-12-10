package com.redis.spring;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisBListDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context/applicationContext-redis.xml");
		RedisTemplate redisTemplate = (RedisTemplate) context.getBean(RedisTemplate.class);

		redisTemplate.delete("list1");
		redisTemplate.delete("list2");
		
		List<String> nodeList = new ArrayList<String>();
		for(int i=1;i<=5;i++){
			nodeList.add("node" + i);
		}
		leftPushAll(redisTemplate, "list1", nodeList);
		
		redisTemplate.opsForList().leftPop("list1",1,TimeUnit.SECONDS);
		redisTemplate.opsForList().rightPop("list1",1,TimeUnit.SECONDS);
		
		nodeList.clear();
		for(int i=1;i<=3;i++){
			nodeList.add("data"+i);
		}
		
		leftPushAll(redisTemplate, "list2", nodeList);
		redisTemplate.opsForList().rightPopAndLeftPush("list1", "list2");
		redisTemplate.opsForList().rightPopAndLeftPush("list1", "list2", 1 , TimeUnit.SECONDS);
		
		printList(redisTemplate,"list1");
		printList(redisTemplate,"list2");
	}
	
	private static void printList(RedisTemplate redisTemplate, String key) {
		// TODO Auto-generated method stub
		Long size = redisTemplate.opsForList().size(key);
		System.out.println(redisTemplate.opsForList().range(key, 0, size));
	}

	private static void leftPushAll(RedisTemplate redisTemplate,String key,List<String> nodeList){
		Iterator<String> ite = nodeList.iterator();
		while(ite.hasNext()){
			redisTemplate.opsForList().leftPush(key, ite.next());
		}
	}

}
