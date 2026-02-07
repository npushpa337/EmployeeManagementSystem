package com.employee.management.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.employee.management.dto.DepartmentRequest;
import com.employee.management.dto.DepartmentResponse;
import com.employee.management.exception.ResourceNotFoundException;
import com.employee.management.mapper.DepartmentMapper;
import com.employee.management.model.Department;
import com.employee.management.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	private final DepartmentRepository repo;
	
	public DepartmentServiceImpl(DepartmentRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public DepartmentResponse createDepartment(DepartmentRequest req) {
		Department dept = DepartmentMapper.toEntity(req);
		return DepartmentMapper.toResponse(repo.save(dept));
	}

	@Override
	public DepartmentResponse getDepartmentById(Long deptId) {
		Department dept= repo.findById(deptId)
				.orElseThrow(()-> new ResourceNotFoundException("Department is not found with an id: " +deptId));	
		return DepartmentMapper.toResponse(dept);
	}

	@Override
	public List<DepartmentResponse> getAllDepartments() {
		return repo.findAll().stream().map(DepartmentMapper::toResponse).toList();
	}

	@Override
	public void deleteDepartment(Long deptId) {
		Department dept= repo.findById(deptId).orElseThrow(()-> new ResourceNotFoundException("id is not found"));
		repo.delete(dept);
	}

}
