package com.nfcm.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nfcm.boot.dao.PruductDao;
import com.nfcm.boot.entity.Pruduct;

@RestController
public class HelloController {
	@Autowired
	PruductDao pruductDao;
	
	@RequestMapping("/getPrud")
	public Pruduct hello(@RequestParam(required=true)String id){
		return pruductDao.getPrud(Integer.parseInt(id));
	}
	

}
