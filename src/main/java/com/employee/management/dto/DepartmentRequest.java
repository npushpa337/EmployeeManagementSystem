package com.employee.management.dto;

import jakarta.validation.constraints.NotNull;

public class DepartmentRequest {
	
	@NotNull
	private String deptName;
	
	@NotNull
	private String location;

	public DepartmentRequest() {
		
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
