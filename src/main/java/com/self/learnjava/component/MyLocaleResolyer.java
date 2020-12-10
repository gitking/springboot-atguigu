package com.self.learnjava.component;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

/*
 * 国际化
 */
public class MyLocaleResolyer implements LocaleResolver{
	
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String l = request.getParameter("l");
		Locale locale = Locale.getDefault();//默认的,注意这里是代码运行所在的电脑默认的语言,不是浏览器默认的语言,你如果你想实现浏览器默认的语言,应该解析浏览器发送请求的请求头里面的accetp languae
		if (!StringUtils.isEmpty(l)) {
			String[] val = l.split("_");
			locale= new Locale(val[0], val[1]);//用前台传过来的值
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		
	}
}
