package com.employee.management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class EmployeeRequest {
	
	@NotNull
	private String empName;
	
	@NotNull
	@Email(message= "invalid email format")
	private String email;
	
	@NotNull
	private Double salary;
	
	@NotNull
	private Long deptId;
	

	public EmployeeRequest() {

	}
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
}
