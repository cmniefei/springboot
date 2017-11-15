package com.nfcm.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nfcm.boot.dao.PruductDao;
import com.nfcm.boot.entity.Pruduct;

@RestController
public class HelloController {
	@Autowired
	PruductDao pruductDao;
	
	/**
	 * 查询出一条数据并且添加到缓存
	 * @param id
	 * @return
	 */
	@RequestMapping("/getPrud")
	@Cacheable("prudCache")
	public Pruduct getPrud(@RequestParam(required=true)String id){
		System.out.println("如果第二次没有走到这里说明缓存被添加了");
		return pruductDao.getPrud(Integer.parseInt(id));
	}
	/**
	 * 删除一个缓存
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/deletePrud")
	@CacheEvict("prudCache")
	public String deletePrud(@RequestParam(required=true)String id){
		return "SUCCESS";
	}
	
	/**
	 * 添加一条保存的数据到缓存，缓存的key是当前prud的id
	 * @param prud
	 * @return
	 */
	@RequestMapping("/savePrud")
	@CachePut(value="prudCache",key="#result.id +''")
	public Pruduct savePrud(Pruduct prud){
		return prud;
	}
	
	
	/**
	 * 返回结果desc中含有nocache字符串就不缓存
	 * @param id
	 * @return
	 */
	@RequestMapping("/getPrud2")
	@CachePut(value ="prudCache",unless="#result.desc.contains('nocache')")
	public Pruduct getPrud2(@RequestParam(required=true)String id){
		System.out.println("如果走到这里说明，说明缓存没有生效！");
		Pruduct p = new Pruduct(Integer.parseInt(id), "name_nocache"+id, "nocache");
		return p;
	}
	

	
	@RequestMapping("/getPrud3")
	@Cacheable(value ="prudCache",key="#root.targetClass.getName() + #root.methodName + #id")
	public Pruduct getPrud3(@RequestParam(required=true)String id){
		System.out.println("如果第二次没有走到这里说明缓存被添加了");
		return pruductDao.getPrud(Integer.parseInt(id));
	}
	
	
	

}
