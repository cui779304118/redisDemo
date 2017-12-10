package com.redis.java;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class RedisPipeline {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedisClient client = new RedisClient();
		Jedis jedis = client.getJedis();
		Long start = System.currentTimeMillis();
		Pipeline pipeline = jedis.pipelined();
		for(int i=0;i<100000;i++){
			int j=i+1;
			pipeline.set("key" + j, "value" +j);
			pipeline.get("key" + j);
		}
		List result = pipeline.syncAndReturnAll();
		Long end = System.currentTimeMillis();
		System.out.println("耗时： " + (end - start ) + "毫秒");

	}

}
