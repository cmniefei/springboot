package com.nfcm.springboot_jpa;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nfcm.springboot_jpa.dao.UserRepository;
import com.nfcm.springboot_jpa.entity.Order;
import com.nfcm.springboot_jpa.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void queryUserWithOrder() {
		User u = userRepository.findUserByUsername("cm");
		System.out.println(u.getUsername());
		List<Order>  orderList =u.getOrders();
		for(Order order:orderList) {
			System.out.println(order);
		}
	}
	
	@Test
	public void queryPageUser() {
		Sort sort = new Sort(Direction.DESC,"id");//根据id降序
		Pageable pageable = new PageRequest(0, 2, sort);//查询第1页的两条数据
		List<User> userList =userRepository.findUsersPage(pageable);
		for(User user:userList) {
			System.out.println(user);
		}
	}
	
	@Test
	public void updateUser() {
		User user = new User();
		user.setUsername("cm2");
		user.setAge(24);
		userRepository.saveAndFlush(user);
		System.out.println(user);
	}
	
	@Test
	public void queryAllUser() {
		List<User> userList =userRepository.findAllUser2("cm");
		for(User user:userList) {
			System.out.println(user);
		}
	}
	
	@Test
	public void queryByAgeGreaterThanOrUsername() {
		List<User> userList =userRepository.findByAgeGreaterThanOrUsername(11,"zhangsan");
		for(User user:userList) {
			System.out.println(user);
		}
	}
	
	
	@Test
	public void queryUserByName() {
		List<User> userList =userRepository.findByUsernameOrAgeOrderByIdDesc("feige",11);
		for(User user:userList) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testUserAdd() {
		System.out.println(userRepository.findAll().get(0));
	}
	
	

}
