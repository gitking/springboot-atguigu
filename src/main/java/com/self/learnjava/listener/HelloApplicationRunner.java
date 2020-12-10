package com.self.learnjava.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//这个实现类只需要加入到容器中就行了,只用添加个注解@Component就行了
@Component
public class HelloApplicationRunner implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("ApplicationRunner...run...");
	}
}
