package com.employee.management.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.employee.management.dto.EmployeeRequest;
import com.employee.management.dto.EmployeeResponse;
import com.employee.management.model.Employee;


public interface EmployeeService {
	
	EmployeeResponse createEmployee(EmployeeRequest req, Long deptId);
	Page<Employee> getAllEmployees(int page, int size, String sortBy, String direction);
	EmployeeResponse getEmployeeById(Long empId);
	List<EmployeeResponse> getAllEmployees();
	List<EmployeeResponse> getEmployeesByDepartment(Long deptId);
	void deleteEmployeeById(Long empId);
}
