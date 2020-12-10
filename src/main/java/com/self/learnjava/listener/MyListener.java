package com.self.learnjava.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("SpringBootWeb应用启动了");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("SpringBootWeb应用注销了");
	}
}
