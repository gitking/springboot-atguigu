package com.self.learnjava.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class HelloSpringApplicationRunListener implements SpringApplicationRunListener{
	
	public HelloSpringApplicationRunListener(SpringApplication application, String[] args) {
		System.out.println("HelloSpringApplicationRunListener必须要有这个带参的构造方法");
	}
	
	@Override
	public void starting() {
		System.out.println("SpringBoot容器启动了...SpringApplicationRunListener");
	}
	
	@Override
	public void environmentPrepared(ConfigurableEnvironment environment) {
		Object o = environment.getSystemProperties().get("os.name");
		System.out.println("SpringApplicationRunListener环境准备好了..." + o);
	}
	
	@Override
	public void contextPrepared(ConfigurableApplicationContext context) {
		System.out.println("IOC容器准备好了..." + context);
	}
	
	@Override
	public void contextLoaded(ConfigurableApplicationContext context) {
		System.out.println("IOC容器加载完成了..." + context);
	}
}
