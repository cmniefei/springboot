package com.nfcm.boot.redis;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nfcm.boot.Application;
import com.nfcm.boot.entity.Pruduct;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisTest {
	
	@Autowired
	RedisConnectionFactory factory;
	
	@Autowired	
	RedisTemplate<String, Object> template;
	
	@Test
	public void test(){
		
	}
	
	@Test
	public void testRedisTemplateList(){
	
		Pruduct prud  = new Pruduct(1, "洗发水", "100ml");
		Pruduct prud2  = new Pruduct(2, "洗面奶", "200ml");
		//依次从尾部添加元素
		template.opsForList().rightPush("pruduct", prud);
		template.opsForList().rightPush("pruduct", prud);
		System.out.println("产品数量:"+template.opsForList().size("pruduct"));
		
		//查询索引0到商品总数-1索引（也就是查出所有的商品）
		List<Object> prodList = template.opsForList().range("pruduct", 0,template.opsForList().size("pruduct")-1);
		for(Object obj:prodList){
			System.out.println((Pruduct)obj);
		}
		
	}
	
	@Test
	public void testRedisTemplate(){
		template.opsForValue().set("key1", "value1");
		System.out.println(template.opsForValue().get("key1"));
	}
	
	@Test
	public void testRedis(){
		//得到一个连接
		RedisConnection conn = factory.getConnection();
		conn.set("hello".getBytes(), "world".getBytes());
		System.out.println(new String(conn.get("hello".getBytes())));
	}

}
