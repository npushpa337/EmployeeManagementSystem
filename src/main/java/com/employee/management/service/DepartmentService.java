package com.employee.management.service;

import java.util.List;

import com.employee.management.dto.DepartmentRequest;
import com.employee.management.dto.DepartmentResponse;

public interface DepartmentService {
	
	DepartmentResponse createDepartment(DepartmentRequest req);
	DepartmentResponse getDepartmentById(Long deptId);
	List<DepartmentResponse> getAllDepartments();
	void deleteDepartment(Long deptId);

}
