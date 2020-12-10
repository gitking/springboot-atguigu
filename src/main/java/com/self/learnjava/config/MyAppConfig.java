package com.self.learnjava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.self.learnjava.service.HelloService;

/*
 * @Configuration指明当前类是一个配置类,就是来替代之前Spring的xml配置文件的,@ImportResource这个注解也不推荐使用了
 * 在之前的Spring xml配置文件中用<bean></bean>标签添加组件对应现在的注解@Bean
 * SpringBoot推荐使用这种方式给容器添加组件,全部使用@Bean注解来添加,不要再编写xml配置文件了。
 */
@Configuration
public class MyAppConfig {

	
	@Bean//将方法的返回值添加到容器中,容器中这个组件默认的id就是方法名
	public HelloService helloService() {
		System.out.println("配置类@Bean给容器添加组件了.....默认是单例的,这个方法只会被运行一次");
		return new HelloService();
	}
}
