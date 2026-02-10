package com.employee.management.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.dto.EmployeeRequest;
import com.employee.management.dto.EmployeeResponse;
import com.employee.management.model.Employee;
import com.employee.management.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee")
@Tag(name= "Employee")
public class EmployeeController {
	
	private final EmployeeService service;
	
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}
	
	@PostMapping("/createEmp/{deptId}")
	@Operation(summary= "create employee by department")
	public ResponseEntity<EmployeeResponse> createEmployee(@PathVariable Long deptId,@Valid @RequestBody EmployeeRequest req){
		return ResponseEntity.ok(service.createEmployee(req, deptId));
	}
	
	@GetMapping("/{empId}")
	@Operation(summary= "Get Employee By Id")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long empId){
		return ResponseEntity.ok(service.getEmployeeById(empId));
	}
	
	@GetMapping
	@Operation(summary= "Get All Employees")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployees(){
		return ResponseEntity.ok(service.getAllEmployees());
	}
	
	@GetMapping("/department/{deptId}")
	@Operation(summary= "Get Employees By Department Id")
	public ResponseEntity<List<EmployeeResponse>> getEmployeesByDepartment(@PathVariable Long deptId){
		return ResponseEntity.ok(service.getEmployeesByDepartment(deptId));
	}
	
	@DeleteMapping("/{empId}")
	@Operation(summary= "Delete Employee By Id")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Long empId){
		service.deleteEmployeeById(empId);
		return ResponseEntity.ok("Employee is deleted successfully");
	}
	
	@GetMapping("/employees")
	public Page<Employee> getAllEmployees(
			@RequestParam(defaultValue = "0")int page,
			@RequestParam(defaultValue = "5")int size,
			@RequestParam(defaultValue = "id")String sortBy,
			@RequestParam(defaultValue = "asc")String direction
	){
		return service.getAllEmployees(page, size, sortBy, direction);			
	}
}
