package com.redis.spring;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisCommand;
import org.springframework.data.redis.core.RedisTemplate;

public class ReisListDemo {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context/applicationContext-redis.xml");
		RedisTemplate redisTemplate = (RedisTemplate) context.getBean(RedisTemplate.class);
		
		try {
			redisTemplate.opsForList().leftPush("list", "node3");
			/*List<String> nodeList = new ArrayList<String>();
			nodeList.add("node1");
			nodeList.add("node2");
			 */
			redisTemplate.opsForList().leftPushAll("list", "node1", "node2");

			redisTemplate.opsForList().rightPush("list", "node4");

			String node1 = (String) redisTemplate.opsForList().index("list", 0);
			Long size = redisTemplate.opsForList().size("list");
			System.out.println(node1 + "," + size + ","
					+ redisTemplate.opsForList().range("list", 0, size));

			String lpop = (String) redisTemplate.opsForList().leftPop("list");
			String rpop = (String) redisTemplate.opsForList().rightPop("list");

			node1 = (String) redisTemplate.opsForList().index("list", 0);
			size = redisTemplate.opsForList().size("list");
			System.out.println(node1 + "," + size + ","
					+ redisTemplate.opsForList().range("list", 0, size));

			//注意：需要使用更为底层的命令才能操作linsert命令

			redisTemplate.opsForList().leftPushIfPresent("list", "head");
			redisTemplate.opsForList().rightPushIfPresent("list", "end");

			redisTemplate.opsForList().set("list", 1, "second");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Long size = redisTemplate.opsForList().size("list");
		System.out.println(redisTemplate.opsForList().range("list", 0, size));
		
	}

}
