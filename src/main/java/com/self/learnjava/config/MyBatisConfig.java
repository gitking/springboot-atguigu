package com.self.learnjava.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/*
 * MyBatis的自定义配置
 * 当com.self.learnjava.mapper这个包下面的类越来越多,需要给每一个类都添加一个@Mapper注解,这太麻烦了,
 * 可以使用@MapperScan这个注解,让他去扫描com.self.learnjava.mapper下面的所有接口,这样就不用给每一个类添加@Mapper了
 * @MapperScan这个注解可以添加在MyBatisConfig这个MyBatis的配置类上面,也可以添加在SpringBootApplication的主类上面
 */
@MapperScan(value="com.self.learnjava.mapper")
@org.springframework.context.annotation.Configuration
public class MyBatisConfig {
	
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return new ConfigurationCustomizer(){
			@Override
			public void customize(Configuration configuration) {
				//开启驼峰命名法,当数据库的表字段跟bean的字段不一样的时候,可以开启这个配置
				configuration.setMapUnderscoreToCamelCase(true);
			}
		};
	}
}
