package com.yaoyaohao.framework.testCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yaoyaohao.framework.redis.ShardedJedisClient;
import com.yaoyaohao.framework.redis.extension.JedisTableClient;
import com.yaoyaohao.framework.redis.extension.impl.JedisTableClientImpl;
import com.yaoyaohao.framework.redis.impl.ShardedJedisClientImpl;

import junit.framework.TestCase;

public class JedisTableClientTestCase extends TestCase {
	public void testJedisTableClient() throws Exception{
		ShardedJedisClient redisClient = new ShardedJedisClientImpl("172.16.8.1",6379);
		
		String tableConfigPath = "/config/redis/paramtables.xml";
		JedisTableClient jedisTableClient = new JedisTableClientImpl(tableConfigPath, redisClient);
		
		/**缓存数据集*/
		//模仿多个数据集
		List<Map<String, Object>> records = new ArrayList<Map<String,Object>>();
		//1
		Map<String,Object> user1 = new HashMap<String, Object>();
		user1.put("USER_ID", "1001");
		user1.put("USER_NO", "1");
		user1.put("USER_NAME", "张三");
		user1.put("AGE", 24);
		user1.put("ADDR", "中国上海");
		records.add(user1);
		//2
		Map<String,Object> user2 = new HashMap<String, Object>();
		user2.put("USER_ID", "1002");
		user2.put("USER_NO", "2");
		user2.put("USER_NAME", "张四");
		user2.put("AGE", 23);
		user2.put("ADDR", "中国北京");
		records.add(user2);
		//3
		Map<String,Object> user3 = new HashMap<String, Object>();
		user3.put("USER_ID", "1003");
		user3.put("USER_NO", "3");
		user3.put("USER_NAME", "李四");
		user3.put("AGE", 24);
		user3.put("ADDR", "中国浙江杭州");
		records.add(user3);
		//4
		Map<String,Object> user4 = new HashMap<String, Object>();
		user4.put("USER_ID", "1004");
		user4.put("USER_NO", "4");
		user4.put("USER_NAME", "王五");
		user4.put("AGE", 25);
		user4.put("ADDR", "中国江苏南京");
		records.add(user4);
		//
		jedisTableClient.loadParam("T_USER", records);
		
		//取缓存数据
		System.out.println("按表查询所有缓存记录：");
		List<Map<String, String>> allRecords = jedisTableClient.getAll("T_USER");
		for(Map<String,String> record : allRecords) {
			System.out.println(record);
		}
		
		//根据索引字段查找缓存记录
		System.out.println("查询缓存表中[user_id = 1003,user_no=3]的记录：");
		Map<String,String> recordData = jedisTableClient.getData("T_USER", "USER_ID", "1002");
		System.out.println(recordData);
		
		//按表清空缓存记录
		jedisTableClient.clearTable("T_USER");
	}
}
