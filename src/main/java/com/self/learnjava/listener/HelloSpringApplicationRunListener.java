package com.self.learnjava.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * SpringApplicationRunListener这个接口一共有7个default方法,分别应SpringBoot启动的7个阶段,这个7个方法你都可以重写
 * 这个类必须配置到src/main/resources/META-INF/spring.factories文件里面才会生效
 * @author issuser
 */
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
	
	@Override
	public void failed(ConfigurableApplicationContext context, Throwable exception) {
		System.out.println("SpringBoot IOC容器加载失败了,发生异常了...");
		if (exception != null) {
			exception.printStackTrace();
		}
	}
}
