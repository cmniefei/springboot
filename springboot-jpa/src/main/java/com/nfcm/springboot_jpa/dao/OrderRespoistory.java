package com.nfcm.springboot_jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nfcm.springboot_jpa.entity.Order;

public interface OrderRespoistory extends JpaRepository<Order, Long>{

}
