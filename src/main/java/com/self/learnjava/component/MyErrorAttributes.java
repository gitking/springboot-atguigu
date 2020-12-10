package com.self.learnjava.component;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

/*
 * 将我们自己定制的错误数据返回出去
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes{
	
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
		map.put("company", "atguigu");
		//获取我们异常处理器MyExceptionHandler携带的数据
		System.out.println("11111");
		
		/**
		 * @PathVariable
		 * http://localhost:8089/springboot/hello?user=aaa
		 * 这里reqMap如果获取不到数据,注意检查HelloController里面的
		 * public String hello(@RequestParam("user")String name) {
		 * 不能写成
		 * public String hello(@@PathVariable("user")String name) {
		 * 写成@PathVariable这里就获取不到数据
		 */
		Map<String, Object> reqMap = (Map<String, Object>)webRequest.getAttribute("ext1111", RequestAttributes.SCOPE_REQUEST);
		System.out.println(reqMap);
		map.put("ext", reqMap);
		return map;
	}
}
