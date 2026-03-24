package com.employee.management.mapper;

import com.employee.management.dto.DepartmentRequest;
import com.employee.management.dto.DepartmentResponse;
import com.employee.management.model.Department;

public class DepartmentMapper {
	
	public static DepartmentResponse toResponse(Department dept) {
		DepartmentResponse response= new DepartmentResponse();
		response.setDeptId(dept.getDeptId());
		response.setDeptName(dept.getDeptName());
		response.setLocation(dept.getLocation());
		return response;
	}
	
	public static Department toEntity(DepartmentRequest req) {
		Department dept = new Department();
		dept.setDeptName(req.getDeptName());
		dept.setLocation(req.getLocation());
		return dept;
	}

}
