package com.nfcm.springboot_jpa.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Configuration
@EnableJpaRepositories(basePackages="com.nfcm.springboot_jpa.dao")//扫描包下继承JpaRepository的接口自动生成实现类
public class JpaConfig {
	
	/**
	 * @PersistenceUnit和@PersistenceContext并不是Spring的注解，
		它们是由JPA规范提供的。 为了让Spring理解这些注解
	 * @return
	 */
	@Bean
	public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}
	/**
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://39.106.22.0:3306/boottest");
		dataSource.setUsername("root");
		dataSource.setPassword("sorry");
		return dataSource;
	}
	
	/**
	 * 容器管理的JPA
	 * @param dataSource
	 * @param vendorAdapter 哪一个厂商的jpa实现
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource,JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean factoryBean=
				new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		//查找带有@Entity注解的类
		factoryBean.setPackagesToScan("com.nfcm.**.entity");
		return factoryBean;
		
	}
	
	/**
	 * 设置jpa具体的实现
	 * @return
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		//设置hibernate的jpa实现
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		//是否打印sql
		adapter.setShowSql(true);
		//如果数据库表不存在就新建
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adapter;
		
	}
	

}
