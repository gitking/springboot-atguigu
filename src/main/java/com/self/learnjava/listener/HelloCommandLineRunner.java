package com.self.learnjava.listener;

//import org.assertj.core.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//这个实现类只需要加入到容器中就行了,只用添加个注解@Component就行了
@Component
public class HelloCommandLineRunner implements CommandLineRunner{
	@Override
	public void run(String... args) throws Exception {
	//	System.out.println("CommandLineRunner...run..." + Arrays.asList(args));
	}
}
