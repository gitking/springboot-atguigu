package com.self.learnjava;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.self.learnjava.bean.Person;

/**
 * SpringBoot单元测试:可以在测试期间很方便的类似编码一样进行自动注入
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest 
{
	@Autowired
	Person person;
	
	@Autowired
	ApplicationContext ioc;
	
	@Autowired
	DataSource datasource;
	
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testHelloService(){
		boolean b = ioc.containsBean("helloService");
		System.out.println("测试@ImportResource的作用" + b);
	}
    /**
     * Rigorous Test :-)
     */
	@Test
    public void shouldAnswerWithTrue()
    {
		System.out.println("SpringBoot的单元测试[" + person);
    }
	
	@Test
	public void logTest() {
		/*
		 * 日志的级别
		 * 由低到高 trace<debug<info<warn<error
		 * 可以调整输出的日志级别,日志就只会在这个级别以后的高级别生效
		 */
		logger.trace("这是trace跟踪轨迹的日志");
		logger.debug("这是debug的日志");
		/*
		 * SpringBoot默认给我们使用的是info级别的日志
		 * 可以自己手工调整,在配置文件里面可以通过logging.level.com.self.learnjava=trace
		 * logging.level.com.self.learnjava=trace的意思是com.self.learnjava这个包下面的日志级别都调整为trace
		 */
		logger.info("这是info级别的日志");
		logger.warn("这是warn警告的日志");
		logger.error("这是error级别的日志");
	}
	
	@Test
	public void contextLoads() throws SQLException {
		System.out.println(datasource.getClass());
		Connection connection = datasource.getConnection();
		System.out.println(connection);
		connection.close();
	}
}
