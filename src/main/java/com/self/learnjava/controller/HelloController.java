package com.self.learnjava.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.self.learnjava.exception.UserNotExistException;

/*
 * @ResponseBody还能写在类上面,意思是这个类的所有方法返回的数据直接写给浏览器,如果返回数据是对象会自动转为json数据。
 * 如果你每次都在类上面写@ResponseBody和@Controller也麻烦,可以直接写成@RestController
 */
@Controller
public class HelloController {
	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/*
	 * 注意我们项目里面有俩个index.html文件,如果不写这个方法,你访问http://localhost:8089/springboot,SpringBoot默认找的是静态资源下public/index.html这个页面
	 * 但是我们想默认访问这个页面怎么办？就写这个方法,接管/换个/index.html这俩个请求,返回一个index,然后模板引擎会自动去templates下面找index.html页面
	 * 但是每次都为这个东西写这么一个空方法也没必要，可以在视图解析器里面直接配置一下MyMvcConfig
	 * 当然最好不要有俩一个名称一样的文件
	 */
//	@RequestMapping({"/", "/index.html"})
//	public String index(){
//		return "index";
//	}
	@ResponseBody
	@RequestMapping("/hello")
	public String hello(@RequestParam("user")String name) {
		System.out.println("@RequestParam" + name);
		if ("aaa".equals(name)){
			throw new UserNotExistException();
		}
		return "SpringBootHelloWorld";
	}
	
	@RequestMapping("/thymeleaf")
	public String testThymeleaf(Map<String, Object> map) {
		map.put("hello", "<h1>你好</h1>");
		map.put("users", Arrays.asList("zhangsan", "lisi","联想是否乱码"));
		//thymeleaf会默认去classpath:/templates/ 下面找 success.html的页面
		return "success";
	}
	
	@ResponseBody
	@GetMapping("/jdbcQuery")
	public Map<String, Object> map() {
		List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from department");
		return list.get(0);
	}
}
