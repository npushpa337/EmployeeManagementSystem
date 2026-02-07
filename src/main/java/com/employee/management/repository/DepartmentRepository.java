package com.employee.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.management.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
//	@Query("SELECT d from Department d WHERE deptName =:deptName")
//	@Query(value= "SELECT d.* from departments where d.deptName = :deptName", nativeQuery = true)
//	Department findByName(String deptName);

}
