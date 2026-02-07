package com.employee.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.model.Department;
import com.employee.management.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	List<Employee> findByDepartment(Department department);
	boolean existsByEmail(String email);
}
