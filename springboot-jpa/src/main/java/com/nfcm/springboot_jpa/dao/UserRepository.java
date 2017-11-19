package com.nfcm.springboot_jpa.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nfcm.springboot_jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findUserByUsername(String username);
	
	User readXxxByUsername(String username);
	
	List<User> findByUsernameOrAgeOrderByIdDesc(String username,int age);
	
	/**
	 * 查找年纪大于某个数或者名字等于某个值得
	 * @param age
	 * @param username
	 * @return
	 */
	List<User> findByAgeGreaterThanOrUsername(int age,String username);
	
	/**
	 * ?1代表第一个参数
	 * @param username
	 * @return
	 */
	@Query("select u from User u where username like %?1%")
	List<User> findAllUser(String username);
	
	
	@Query("select u from User u where username like %:username%")
	List<User> findAllUser2(@Param("username")String username);
	
	/**
	 * 分页查询
	 * @param page
	 * @return
	 */
	@Query("select u from User u")
	List<User> findUsersPage(Pageable page);
	

}
