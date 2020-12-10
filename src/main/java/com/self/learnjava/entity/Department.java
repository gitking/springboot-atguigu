package com.self.learnjava.entity;

public class Department {
	private Integer id;
	private String departmentName;
	
	public Department() {
		
	}
	
	public Department(int i, String deaprtmentName) {
		this.id = i;
		this.departmentName = deaprtmentName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + this.departmentName + "]";
	}
}
