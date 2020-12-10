package com.self.learnjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.self.learnjava.bean.Department;
import com.self.learnjava.bean.Employee;
import com.self.learnjava.mapper.DepartmentMapper;
import com.self.learnjava.mapper.EmployeeMapper;

@RestController
public class DeptController {

	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	/*
	 * 在页面上访问这个地址就可以访问数据了
	 * http://localhost:8080/dept/1
	 */
	@GetMapping("/dept/{id}")
	public Department getDepartment(@PathVariable("id")Integer id) {
		return departmentMapper.getDeptById(id);
	}
	
	/*
	 * 在页面上访问这个地址就可以直接插入一条数据了
	 * http://localhost:8080/dept?departmentName=cc
	 */
	@GetMapping("/dept")
	public Department insertDept(Department department){
		departmentMapper.insertDept(department);
		return department;
	}
	
	@GetMapping("/emp/{id}")
	public Employee getEmp(@PathVariable("id")Integer id) {
		return employeeMapper.getEmpById(id);
	}
}
