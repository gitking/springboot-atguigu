package com.self.learnjava.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/*
 * 登录检查
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
	
	/*
	 * 在执行目标方法之前
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object user = request.getSession().getAttribute("loginUser");
		if (user != null) {
			return true;
		} else {//未登录
			request.setAttribute("msg", "没有权限,请先登录");
			request.getRequestDispatcher("/index.html").forward(request, response);//未登录的用户就转发到首页
			return false;
		}
	}
}
