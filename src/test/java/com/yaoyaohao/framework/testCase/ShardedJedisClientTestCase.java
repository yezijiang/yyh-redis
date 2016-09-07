package com.yaoyaohao.framework.testCase;

import java.util.HashMap;
import java.util.Map;

import com.yaoyaohao.framework.redis.ShardedJedisClient;
import com.yaoyaohao.framework.redis.impl.ShardedJedisClientImpl;

import junit.framework.TestCase;

public class ShardedJedisClientTestCase extends TestCase {
	public void testShardedJedisClient() throws Exception {
		ShardedJedisClient redisClient = new ShardedJedisClientImpl("172.16.8.1",6379);
		Object result = redisClient.hgetObject("MMS","ss");
		System.out.println(result);
		
//		System.out.println(redisClient.getObject("YEB:SPES:ACT_LIST_AL00003"));
//		
		System.out.println(redisClient.keys("*"));
//		//
		Map<String,String> map = new HashMap<String, String>();
		map.put("NAME", "JACK");
		map.put("AGE", "10");
		map.put("ADDR", "");
		result = redisClient.hmset("user_1001", map);
		System.out.println("hmset 结果 ： "+result);
		
		System.out.println(redisClient.hdel("user_1001", "NAME"));
		
		System.out.println(redisClient.hgetAll("user_1001"));
		System.out.println(redisClient.hget("user_1001", "ADDR"));
//		redisClient.destory();
//	}
//	
//	public void testShardedJedisClientExpire() throws Exception {
//		String configPath = "/config/redis/redis-config.xml";
//		ShardedJedisClient redisClient = new ShardedJedisClientImpl(configPath);
//		
//		redisClient.set("authId1", "xxxxxxxxxxxx");
//		System.out.println(redisClient.ttl("authId1"));
//		redisClient.expire("authId1", 30);
//		System.out.println(redisClient.ttl("authId1"));
//		Thread.sleep(2000);
//		System.out.println(redisClient.ttl("authId1"));
//		redisClient.expire("authId1", 30);
//		System.out.println(redisClient.ttl("authId1"));
//		redisClient.del("authId1");
//		
//		redisClient.sadd("SET1", "123","124");
//		System.out.println(redisClient.sismember("SET1", "123"));
//		System.out.println(redisClient.sismember("SET1", "111"));
//		System.out.println(redisClient.sismember("SET2", "123"));
//		redisClient.srem("SET1", "123","124");
//		System.out.println(redisClient.sismember("SET1", "123"));
//		System.out.println(redisClient.sismember("SET1", "124"));
//		System.out.println(redisClient.sismember("SET1", "111"));
		redisClient.destory();
	}
}
