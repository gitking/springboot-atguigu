package com.self.learnjava.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

//这个实现类需要配置到classpath下面的META-INF/spring.factories文件里面,就像SpringBoot自己JAR包下面那样
//可以参考这个jar包里面的META-INF/spring.factories文件,spring-boot-autoconfigure-2.3.0.RELEASE.jar
public class HelloApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>{
	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		System.out.println("SpringBoot的ApplicationContextInitializer初始化了" + applicationContext);
	}
}
