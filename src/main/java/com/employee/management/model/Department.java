package com.employee.management.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="department")
public class Department {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long deptId;
	
	@Column(nullable= false)
	private String deptName;
	
	@OneToMany(mappedBy="department")
	@JsonIgnore
	private List<Employee> employees= new ArrayList<>();

	public Department() {
		super();
	}

	public Department(String deptName,  List<Employee> employees) {
		super();
		this.deptName = deptName;
		this.employees = employees;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}
