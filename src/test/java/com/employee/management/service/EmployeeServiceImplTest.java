package com.employee.management.service;

import com.employee.management.dto.EmployeeRequest;
import com.employee.management.dto.EmployeeResponse;
import com.employee.management.exception.ResourceNotFoundException;
import com.employee.management.model.Department;
import com.employee.management.model.Employee;
import com.employee.management.repository.DepartmentRepository;
import com.employee.management.repository.EmployeeRepository;
import com.employee.management.service.EmployeeServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee_Success() {
        EmployeeRequest request = new EmployeeRequest();
        request.setEmpName("Pushpa");
        request.setEmail("pushpa@gmail.com");

        Department dept = new Department();
        dept.setDeptId(1L);
        dept.setDeptName("IT");

        Employee employee = new Employee();
        employee.setEmpName("Pushpa");
        employee.setEmail("pushpa@gmail.com");
        employee.setDepartment(dept);

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(dept));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponse response = employeeService.createEmployee(request, 1L);

        assertNotNull(response);
        assertEquals("Pushpa", response.getEmpName());
        verify(departmentRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testCreateEmployee_DepartmentNotFound() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> employeeService.createEmployee(new EmployeeRequest(), 1L));
        assertEquals("Department not found with the id: 1", ex.getMessage());
    }


    @Test
    void testGetEmployeeById_Success() {
        Employee employee = new Employee();
        employee.setEmpId(1L);
        employee.setEmpName("Pushpa");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeResponse response = employeeService.getEmployeeById(1L);

        assertNotNull(response);
        assertEquals("Pushpa", response.getEmpName());
        verify(employeeRepository).findById(1L);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,() -> employeeService.getEmployeeById(1L));

        assertEquals("Employee not found with the id: 1", ex.getMessage());
    }


    @Test
    void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        Employee e1 = new Employee(); 
        e1.setEmpName("A");
        Employee e2 = new Employee(); 
        e2.setEmpName("B");

        employees.add(e1);
        employees.add(e2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeResponse> responses = employeeService.getAllEmployees();

        assertEquals(2, responses.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeesByDepartment_Success() {
        Department dept = new Department();
        dept.setDeptId(1L);
        dept.setDeptName("IT");

        Employee emp = new Employee();
        emp.setEmpName("Pushpa");
        emp.setDepartment(dept);

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(dept));
        when(employeeRepository.findByDepartment(dept)).thenReturn(List.of(emp));

        List<EmployeeResponse> responses =employeeService.getEmployeesByDepartment(1L);

        assertEquals(1, responses.size());
        assertEquals("Pushpa", responses.get(0).getEmpName());
        verify(employeeRepository, times(1)).findByDepartment(dept);
    }

    @Test
    void testGetEmployeesByDepartment_DepartmentNotFound() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,
                () -> employeeService.getEmployeesByDepartment(1L));

        assertTrue(ex.getMessage().contains("Department not found"));
    }

    @Test
    void testDeleteEmployeeById_Success() {
        Employee emp = new Employee();
        emp.setEmpId(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));
        doNothing().when(employeeRepository).delete(emp);

        employeeService.deleteEmployeeById(1L);

        verify(employeeRepository, times(1)).delete(emp);
    }

    @Test
    void testDeleteEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,() -> employeeService.deleteEmployeeById(1L));

        assertEquals("Employee not found with the id: 1", ex.getMessage());
    }
}
