package com.self.learnjava.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.assertj.core.util.Arrays;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {
	
	@ConfigurationProperties(prefix="spring.datasource")
	@Bean
	public DataSource druid() {
		return new DruidDataSource();
	}
	
	//配置Drid的监控
	//1,配置一个管理后台的Servlet
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean bean =new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		Map<String, String> initParam = new HashMap<>();
		initParam.put("loginUsername", "admin");
		initParam.put("loginPassword", "123456");
		initParam.put("allow", "");//为空等于没配,默认允许所有访问
		initParam.put("deny", "192.168.15.21");//拒绝这个IP地址访问
		bean.setInitParameters(initParam);
		return bean;
	}
	//2,配置一个web监控的filter
	@Bean
	public FilterRegistrationBean webStatFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new WebStatFilter());
		Map<String, String> map = new HashMap<>();
		map.put("exclusions", "*.js, *.css, /druid/*");
		bean.setInitParameters(map);
		bean.setUrlPatterns(Arrays.asList("/*"));
		return bean;
	}
	
}
