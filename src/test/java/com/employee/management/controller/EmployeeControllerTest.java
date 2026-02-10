package com.employee.management.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.management.dto.EmployeeRequest;
import com.employee.management.dto.EmployeeResponse;
import com.employee.management.model.Employee;
import com.employee.management.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    // ---------------- CREATE EMPLOYEE ----------------
    @Test
    void createEmployee_success() throws Exception {

        EmployeeRequest request = new EmployeeRequest();
        request.setEmpName("Pushpa");
        request.setEmail("pushpa@gmail.com");
        request.setSalary(45000.0);
        request.setDeptId(1L);
        

        EmployeeResponse response = new EmployeeResponse();
        response.setEmpId(1L);
        response.setEmpName("Pushpa");

        Mockito.when(employeeService.createEmployee(any(EmployeeRequest.class), eq(10L)))
                .thenReturn(response);

        mockMvc.perform(post("/api/employee/createEmp/{deptId}", 10L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empId").value(1L))
                .andExpect(jsonPath("$.empName").value("Pushpa"));
    }

    // ---------------- GET EMPLOYEE BY ID ----------------
    @Test
    void getEmployeeById_success() throws Exception {

        EmployeeResponse response = new EmployeeResponse();
        response.setEmpId(1L);
        response.setEmpName("Rashmi");

        Mockito.when(employeeService.getEmployeeById(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/employee/{empId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empId").value(1L))
                .andExpect(jsonPath("$.empName").value("Rashmi"));
    }

    // ---------------- GET ALL EMPLOYEES ----------------
    @Test
    void getAllEmployees_success() throws Exception {

        EmployeeResponse e1 = new EmployeeResponse();
        e1.setEmpId(1L);
        e1.setEmpName("A");
        EmployeeResponse e2 = new EmployeeResponse();
        e2.setEmpId(1L);
        e2.setEmpName("B");

        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(List.of(e1, e2));

        mockMvc.perform(get("/api/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].empName").value("A"))
                .andExpect(jsonPath("$[1].empName").value("B"));
    }

    // ---------------- GET EMPLOYEES BY DEPARTMENT ----------------
    @Test
    void getEmployeesByDepartment_success() throws Exception {

        EmployeeResponse e1 = new EmployeeResponse();
        e1.setEmpId(1L);
        e1.setEmpName("pushpa");
        EmployeeResponse e2 = new EmployeeResponse();
        e2.setEmpId(1L);
        e2.setEmpName("Nayana");       

        Mockito.when(employeeService.getEmployeesByDepartment(5L))
                .thenReturn(List.of(e1, e2));

        mockMvc.perform(get("/api/employee/department/{deptId}", 5L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    // ---------------- DELETE EMPLOYEE ----------------
    @Test
    void deleteEmployeeById_success() throws Exception {

        Mockito.doNothing().when(employeeService).deleteEmployeeById(1L);

        mockMvc.perform(delete("/api/employee/{empId}", 1L))
                .andExpect(status().isOk());
    }

    // ---------------- PAGINATION ----------------
    @Test
    void getAllEmployees_withPagination_success() throws Exception {

        Employee emp1 = new Employee();
        emp1.setEmpId(1L);
        emp1.setEmpName("A");

        Employee emp2 = new Employee();
        emp2.setEmpId(2L);
        emp2.setEmpName("B");

        Page<Employee> page = new PageImpl<>(
                List.of(emp1, emp2),
                PageRequest.of(0, 5),
                2
        );

        Mockito.when(employeeService.getAllEmployees(0, 5, "id", "asc"))
                .thenReturn(page);

        mockMvc.perform(get("/api/employee/employees")
                .param("page", "0")
                .param("size", "5")
                .param("sortBy", "id")
                .param("direction", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value(2))
                .andExpect(jsonPath("$.content[0].empName").value("A"));
    }
}
