package com.employee.management.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employee.management.dto.EmployeeRequest;
import com.employee.management.dto.EmployeeResponse;
import com.employee.management.exception.BadRequestException;
import com.employee.management.exception.ResourceNotFoundException;
import com.employee.management.mapper.EmployeeMapper;
import com.employee.management.model.Department;
import com.employee.management.model.Employee;
import com.employee.management.repository.DepartmentRepository;
import com.employee.management.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository repo;
	private final DepartmentRepository deptRepo;
	
	public EmployeeServiceImpl(EmployeeRepository repo, DepartmentRepository deptRepo) {

		this.repo = repo;
		this.deptRepo = deptRepo;
	}

	@Override
	public EmployeeResponse createEmployee(EmployeeRequest req, Long deptId) {
		
		Department dept= deptRepo.findById(deptId)
				.orElseThrow(()-> new ResourceNotFoundException("Department not found with the id: " + deptId));
		if(repo.existsByEmail(req.getEmail())) {
			throw new BadRequestException("Email id is already exists");
		}
		Employee emp = EmployeeMapper.toEntity(req, dept);
		return EmployeeMapper.toResponse(repo.save(emp));
	}

	@Override
	public EmployeeResponse getEmployeeById(Long empId) {
		Employee emp= repo.findById(empId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found with the id: " + empId));
		return EmployeeMapper.toResponse(emp);
	}

	@Override
	public List<EmployeeResponse> getAllEmployees() { 
		return repo.findAll().stream().map(EmployeeMapper::toResponse).toList();
	}

	@Override
	public List<EmployeeResponse> getEmployeesByDepartment(Long deptId) {
		Department dept= deptRepo.findById(deptId)
				.orElseThrow(()-> new ResourceNotFoundException("Department not found with the id: " + deptId));
		return repo.findByDepartment(dept).stream().map(EmployeeMapper::toResponse).toList();
	}

	@Override
	public void deleteEmployeeById(Long empId) {
		Employee emp= repo.findById(empId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found with the id: " + empId));
		repo.delete(emp);
	}
	
	
	@Override
	public Page<Employee> getAllEmployees(int page, int size, String sortBy, String direction) {
		Sort sort= direction.equalsIgnoreCase("desc")? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
		
		Pageable pageable= PageRequest.of(page, size, sort); 
		return repo.findAll(pageable);
	}
		

	

}
