package com.self.learnjava.config;

import java.util.Arrays;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.self.learnjava.component.LoginHandlerInterceptor;
import com.self.learnjava.component.MyLocaleResolyer;
import com.self.learnjava.filter.MyFilter;
import com.self.learnjava.listener.MyListener;
import com.self.learnjava.servlet.MyServlet;

/*
 * 最初可以通过继承WebMvcConfigurerAdapter这个类扩展SpringMVC,现在这个类已经废弃了,java8之后接口里面可以有默认方法了,
 * 所以现在推荐直接继承WebMvcConfigurer这个接口,WebMvcConfigurer这个接口里面都是默认方法
 * 这里不能添加@EnableWebMvc这个注解，如果添加了@EnableWebMvc就代表全面结果SpringMVC,SpringBoot关于SpringMVC的所有默认自动配置全部失效了
 * 不推荐全面接管SpringMVC
 * 为什么添加了@EnableWebMvc这个注解,SpringMVC所有默认功能就失效了呢？
 * 是因为@EnableWebMvc这个注解会往容器中导入一个@Import(DelegatingWebMvcConfiguration.class),这个类DelegatingWebMvcConfiguration又继承了WebMvcConfigurationSupport
 * 这个类,而SpringBoot关于SpringMVC的所有默认自动配置的功能都是通过WebMvcAutoConfiguration这个类实现的,WebMvcAutoConfiguration这个类上面有一个注解@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
 * 当容器中不存在WebMvcConfigurationSupport这个类时才能生效,所以一启用@EnableWebMvc,SpringBoot关于SpringMVC的所有默认自动配置就全部失效了
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		/*
		 * 视图解析器,把这个请求/atguigu映射到success页面,
		 * 浏览器发送/atguigu请求,也跳转到success页面
		 */
		registry.addViewController("/atguigu").setViewName("success");
	}
	
	/*
	 * 另外一种写法
	 * 我们之前看过WebMvcAutoConfiguration的源码知道,所有的WebMvcConfigurer组件都会一起生效
	 */
	@Bean
	public WebMvcConfigurer createWebMvcConfigurer() {
		return new WebMvcConfigurer(){
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("index");
				registry.addViewController("/index.html").setViewName("index");
				registry.addViewController("/main.html").setViewName("dashboard");
			}
			
			/*
			 * 注册拦截器
			 */
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				
				/*
				 * 静态资源css,js,SpringBoot已经做好了静态资源映射了,这里不需要排除这些静态资源的请求
				 */
				registry.addInterceptor(new LoginHandlerInterceptor())
				.addPathPatterns("/**")//拦截所有请求
				.excludePathPatterns("/index.html", "/", "/user/login");//排除这些请求,这些请求不过滤
			}
		};
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new MyLocaleResolyer();
	}
	
	
	/*
	 * 定制嵌入式的Servlet容器相关规则
	 * EmbeddedServletContainerCustomizer这个类SpringBoot2.x版本已经没有这个类了被WebServerFactoryCustomizer替代了
	 */
	@Bean
	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
		return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>(){
			@Override
			public void customize(ConfigurableWebServerFactory factory) {
				factory.setPort(8083);//修改tomcat的端口号
			}
		};
	}
	/*
	 * EmbeddedServletContainerCustomizer这个类SpringBoot2.x版本已经没有这个类了被WebServerFactoryCustomizer替代了
	 */
//	@Bean
//	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
//		return new EmbeddedServletContainerCustomizer(){
//			private void customize() {
//
//			}
//		};
//	}
	
	
	/*
	 * 注册Servlet
	 */
	@Bean
	public ServletRegistrationBean myServlet() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(), "/myServlet");
		registrationBean.setLoadOnStartup(1);//启动顺序
		return registrationBean;
	}
	
	
	/**
	 * 注册Filter
	 * 注意这个构造方法,这个构造方法的意思是拦截指定的servlet
	 * public FilterRegistrationBean(T filter, ServletRegistrationBean<?>... servletRegistrationBeans) {
		super(servletRegistrationBeans);
		Assert.notNull(filter, "Filter must not be null");
		this.filter = filter;
	}
	 * @return
	 */
	@Bean
	public FilterRegistrationBean myFilter() {
		FilterRegistrationBean registrataionBean = new FilterRegistrationBean();
		registrataionBean.setFilter(new MyFilter());
		registrataionBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
		return registrataionBean;
	}
	
	/*
	 * 注册Listener
	 */
	@Bean
	public ServletListenerRegistrationBean myListener() {
		ServletListenerRegistrationBean<MyListener> registrationListener = new ServletListenerRegistrationBean<MyListener>(new MyListener());
		return registrationListener;
	}
}
