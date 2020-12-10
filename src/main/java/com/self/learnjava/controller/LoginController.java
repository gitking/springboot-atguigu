package com.self.learnjava.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	/*
	 * @RequestMapping如果不指定method,那么默认什么请求都可以处理
	 * 400错误一般都是客户端错误,就是浏览器那边的错误,要么是参数传的不对,要么是请求路径不对404
	 */
	@RequestMapping(value="/user/login", method = RequestMethod.POST)
	public String login(@RequestParam("username")String username, @RequestParam("password") String password,
			Map<String, Object> map, HttpSession session) {
		if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
			//return "dashboard";//登录成功,登录成功跳转到dashboard页面,如果用户刷新了当前页面,容易造成重复提交表单
			//解决重复提交表单的办法就是,使用重定向
			session.setAttribute("loginUser", username);
			return "redirect:/main.html";//重定向这里,然后有MyMvcConfig这个类再把main.html映射到dashboard页面
		} else {//登录失败
			map.put("msg", "用户名密码错误");
			return "index";
		}
	}
}
