package com.yaoyaohao.framework.testCase;

import java.util.Set;

import junit.framework.TestCase;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolTestCase extends TestCase {
	public void testJedisPool() throws Exception{
//		JedisPoolConfig config = new JedisPoolConfig();
//		config.setMaxTotal(20);
//		config.setMaxIdle(5);
		String host = "172.16.8.1";
		int port = 6379;
		JedisPool jedisPool = new JedisPool(host,port);
		Jedis jedis = jedisPool.getResource();
		jedis.set("hello", "world");
		Set<String> keys = jedis.keys("*");
		for(String key : keys) {
			System.out.println(key + " : " + jedis.get(key));
		}
	}
}
