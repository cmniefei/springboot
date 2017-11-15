package com.nfcm.thymeleaf.entity;

public class User {
	
	private String id;
	private String name;
	private int age;
	private String desc;
	private String role;
	
	public User(String id, String name, int age, String desc,String role) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.desc = desc;
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
