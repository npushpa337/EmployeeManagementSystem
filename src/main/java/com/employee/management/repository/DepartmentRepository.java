package com.employee.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.management.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	

}
