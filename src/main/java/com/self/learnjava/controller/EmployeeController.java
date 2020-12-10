package com.self.learnjava.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.self.learnjava.dao.DepartmentDao;
import com.self.learnjava.dao.EmployeeDao;
import com.self.learnjava.entity.Department;
import com.self.learnjava.entity.Employee;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	DepartmentDao departmentDao;
	
	/**
	 * 查询所有员工页面
	 * @return
	 */
	@GetMapping("/emps")
	public String list(Model model) {
		Collection<Employee> employees = employeeDao.getAll();
		model.addAttribute("emps", employees);//将数据放在请求域中
		//thymeleaf模板引擎会自动去classpath/templates/emp目录下面去找页面
		return "emp/list";
	}
	
	/*
	 * 跳转添加员工页面
	 */
	@GetMapping("/emp")
	public String toAddPage(Model model){
		//查出所有员工部门,给添加员工的页面使用
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("depts", departments);
		return "emp/add";
	}
	/*
	 * 添加员工
	 * ThymeleafViewResolver,可以参考这个类里面的createView方法,这个方法会判断返回出去的viewname是以什么开头的
	 */
	@PostMapping("/emp")
	public String addEmp(Employee employee) {
		employeeDao.save(employee);//保存员工
		//return "/emps";不能这样写,这样写就被thymeleaf模板引擎拦截了
		//return "forward:/emps";//应该这样写,才能正确转发到/emps这个请求
		return "redirect:/emps";//应该这样写,才能正确转发到/emps这个请求
	}
	
	/*
	 * 编辑员工信息,需要将员工信息返回到页面上去
	 */
	@GetMapping("/emp/{id}")
	public String toEditPage(@PathVariable("id")Integer id, Model model) {
		Employee employee = employeeDao.get(id);
		model.addAttribute("emp", employee);
		//查出所有员工部门,给添加员工的页面使用
		Collection<Department> departments = departmentDao.getDepartments();
		model.addAttribute("depts", departments);
		return "emp/add";//员工添加页面和修改页面是一样的
	}
	
	//员工修改
	@PutMapping("/emp")
	public String updateEmployee(Employee employee) {
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@DeleteMapping("/emp1/{id}")//员工删除
	public String delEmployee(@PathVariable("id") Integer id) {
		employeeDao.delete(id);
		return "redirect:/emps";
	}
}
