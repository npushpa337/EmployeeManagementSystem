package com.emp.management.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.employee.management.dto.DepartmentRequest;
import com.employee.management.dto.DepartmentResponse;
import com.employee.management.exception.ResourceNotFoundException;
import com.employee.management.model.Department;
import com.employee.management.repository.DepartmentRepository;
import com.employee.management.service.DepartmentServiceImpl;

class DepartmentServiceImplTest{
	
	@Mock
	private DepartmentRepository departmentRepo;
	
	@InjectMocks
	private DepartmentServiceImpl departmentService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testCreateDepartment() {
		
		DepartmentRequest request = new DepartmentRequest();
		request.setDeptName("HR");
		
		Department dept= new Department();
		dept.setDeptId(1L);
		dept.setDeptName("HR");
	
		
		when(departmentRepo.save(any(Department.class))).thenReturn(dept);
		DepartmentResponse response= departmentService.createDepartment(request);
		
		assertNotNull(response);
		assertEquals("HR", response.getDeptName());
		verify(departmentRepo, times(1)).save(any(Department.class));	
	}
	
	@Test
	void testGetDepartmentById_Success() {
		
		Department dept= new Department();
		dept.setDeptId(1L);
		dept.setDeptName("HR");
		
		when(departmentRepo.findById(1L)).thenReturn(Optional.of(dept));
		DepartmentResponse response= departmentService.getDepartmentById(1L);
		
		assertNotNull(response);
		assertEquals("HR", response.getDeptName());
		verify(departmentRepo, times(1)).findById(1L);
	}
	
	@Test
	void testGetDepartmentById_NotFound() {
		
		when(departmentRepo.findById(1L)).thenReturn(Optional.empty());
		
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> departmentService.getDepartmentById(1L));
		assertEquals("Department is not found with an id: 1", exception.getMessage());
	}
	
	@Test
    void testDeleteDepartment_Success() {
		
        Department dept = new Department();
        dept.setDeptId(1L);
        dept.setDeptName("HR");
        
        when(departmentRepo.findById(1L)).thenReturn(Optional.of(dept));
        doNothing().when(departmentRepo).delete(dept);
        departmentService.deleteDepartment(1L);
        
        verify(departmentRepo, times(1)).delete(dept);
        
    }
	
	@Test
	void testDeleteDepartmentById_NotFound() {
		
		when(departmentRepo.findById(1L)).thenReturn(Optional.empty());
		
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> departmentService.deleteDepartment(1L));
		assertEquals("id is not found", exception.getMessage());

	}
}
