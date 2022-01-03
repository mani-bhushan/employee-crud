package com.apps.org.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apps.org.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	public Employee findByEmpId(String empId);
	
	public Employee findByEmpName(String empName);
	
	
}