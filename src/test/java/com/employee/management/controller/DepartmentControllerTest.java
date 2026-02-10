package com.employee.management.controller;

import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.employee.management.dto.DepartmentRequest;
import com.employee.management.dto.DepartmentResponse;
import com.employee.management.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    // ---------------- CREATE DEPARTMENT ----------------
    @Test
    void createDepartment_success() throws Exception {

        DepartmentRequest request = new DepartmentRequest();
        request.setDeptName("IT");
        request.setLocation("Banglore");

        DepartmentResponse response = new DepartmentResponse();
        response.setDeptId(1L);
        response.setDeptName("IT");
        response.setLocation("Banglore");

        Mockito.when(departmentService.createDepartment(any(DepartmentRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/dept/createDept")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deptId").value(1L))
                .andExpect(jsonPath("$.deptName").value("IT"));
    }

    // ---------------- GET DEPARTMENT BY ID ----------------
    @Test
    void getDepartmentById_success() throws Exception {

        DepartmentResponse response = new DepartmentResponse();
        response.setDeptId(1L);
        response.setDeptName("HR");

        Mockito.when(departmentService.getDepartmentById(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/dept/{deptId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deptId").value(1L))
                .andExpect(jsonPath("$.deptName").value("HR"));
    }

    // ---------------- GET ALL DEPARTMENTS ----------------
    @Test
    void getAllDepartments_success() throws Exception {

        DepartmentResponse d1 = new DepartmentResponse();
        d1.setDeptId(1L);
        d1.setDeptName("HR");
        
        DepartmentResponse d2 = new DepartmentResponse();
        d2.setDeptId(1L);
        d2.setDeptName("IT");
        
        Mockito.when(departmentService.getAllDepartments())
                .thenReturn(List.of(d1, d2));

        mockMvc.perform(get("/api/dept"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].deptName").value("HR"))
                .andExpect(jsonPath("$[1].deptName").value("IT"));	
    }

    // ---------------- DELETE DEPARTMENT ----------------
    @Test
    void deleteDepartmentById_success() throws Exception {

        Mockito.doNothing().when(departmentService).deleteDepartment(1L);

        mockMvc.perform(delete("/api/dept/{deptId}", 1L))
                .andExpect(status().isOk());
    }
}
