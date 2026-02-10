package com.employee.management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.dto.DepartmentRequest;
import com.employee.management.dto.DepartmentResponse;
import com.employee.management.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/dept")
@Tag(name= "Department")
public class DepartmentController {
	
	private final DepartmentService service;
	
	public DepartmentController(DepartmentService service) {
		this.service = service;
	}
	
	@PostMapping("/createDept")
	@Operation(summary= "create department")
	public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentRequest req){
		DepartmentResponse response= service.createDepartment(req);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{deptId}")
	@Operation(summary= "Get department by Id")
	public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long deptId){
		return ResponseEntity.ok(service.getDepartmentById(deptId));
	}
	
	@GetMapping
	@Operation(summary= "Get all departments")
	public ResponseEntity<List<DepartmentResponse>> getAllDepartments(){
		return ResponseEntity.ok(service.getAllDepartments());
	}
	
	@DeleteMapping("/{deptId}")
	@Operation(summary= "delete department by Id")
	public ResponseEntity<String> deleteDepartmentById(@PathVariable Long deptId){
		service.deleteDepartment(deptId);
		return ResponseEntity.ok("Department is deleted successfully");
	}
}
