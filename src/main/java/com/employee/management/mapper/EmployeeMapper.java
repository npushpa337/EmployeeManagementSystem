package com.employee.management.mapper;

import com.employee.management.dto.EmployeeRequest;
import com.employee.management.dto.EmployeeResponse;
import com.employee.management.model.Department;
import com.employee.management.model.Employee;

public class EmployeeMapper {
	
	public static EmployeeResponse toResponse(Employee employee) {

	    EmployeeResponse response = new EmployeeResponse();
	    response.setEmpName(employee.getEmpName());
	    response.setEmpId(employee.getEmpId());
	    response.setEmail(employee.getEmail());
	    response.setSalary(employee.getSalary());
	    if (employee.getDepartment() != null) {
	        response.setDeptId(employee.getDepartment().getDeptId());
	        response.setDeptName(employee.getDepartment().getDeptName());
	    }
	    return response;
	}

	public static Employee toEntity(EmployeeRequest req, Department dept) {
		Employee emp= new Employee();
		emp.setEmpName(req.getEmpName());
		emp.setSalary(req.getSalary());
		emp.setEmail(req.getEmail());
		emp.setDepartment(dept);
		return emp;
	}
}
