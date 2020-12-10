package com.self.learnjava.mapper;

import com.self.learnjava.bean.Employee;

/*
 * 不管是使用注解的方式或者xml配置文件的方式,第一步都是要先用@Mapper或者@MapperScan先把这些接口扫描到容器中
 * 我们这个使用xml配置文件的形式
 */
public interface EmployeeMapper {
	
	public Employee getEmpById(Integer id);
	
	public void insertEmp(Employee employee);
}
