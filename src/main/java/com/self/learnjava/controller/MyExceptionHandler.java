package com.self.learnjava.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.self.learnjava.exception.UserNotExistException;

/*
 * SpringBoot定制发生错误时返回的json异常数据
 */
@ControllerAdvice
public class MyExceptionHandler {
	
	//这种不管是浏览器还是postman调用返回的都是json数据
//	@ResponseBody
//	@ExceptionHandler(UserNotExistException.class)
//	public Map<String,Object> handleException(Exception e) {
//		Map<String, Object> map = new HashMap<>();
//		map.put("code", "user.notexist");
//		map.put("message", e.getMessage());
//		return map;
//	}
	
	@ExceptionHandler(UserNotExistException.class)
	public String handleException(Exception e, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		/*
		 * 放入我们自己的状态,4xx 5xx,如果不放入状态就不会进入我们自己定制的错误页面
		 * 可以让SpringBoot帮我们做的自适应的效果
		 */
		request.setAttribute("javax.servlet.error.status_code", 500);
		map.put("code", "user.notexist");
		map.put("message", "我自定义的异常数据你看到了吗");
		request.setAttribute("ext", map);
		request.setAttribute("ext1111", map);

		//转发到/error让SpringBoot的BasicErrorController去处理这个请求,可以达到自适应的功能,浏览器访问返回页面,postman返回json数据
		return "forward:/error";
	}
}
