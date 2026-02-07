package com.employee.management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employee" )
public class Employee {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long empId;
	
	@Column(nullable= false)
	private String empName;
	
	@Column(nullable= false, unique= true)
	private String email;
	
	@Column(nullable= false)
	private Double salary;
	
	@JsonIgnoreProperties("employee")
	@ManyToOne
	@JoinColumn(name= "department_Id", nullable= false)
	private Department department;

	public Employee() {
		super();
		
	}

	public Employee(String empName, String email, Double salary, Department department) {
		super();
		this.empName = empName;
		this.email = email;
		this.salary = salary;
		this.department = department;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
