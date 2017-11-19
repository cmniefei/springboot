package com.nfcm.springboot_jpa;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nfcm.springboot_jpa.dao.OrderRespoistory;
import com.nfcm.springboot_jpa.entity.Order;
import com.nfcm.springboot_jpa.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderTest {

	@Autowired
	OrderRespoistory orderRepository;
	
	@Test
	public void addOrder() {
		User user = new User(1,"cm2",22);
		Order order = new Order( "x0002", new Date());
		order.setUser(user);
		orderRepository.save(order);
	}
	
	
}
