package com.apps.org.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apps.org.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {

	public Optional<Employee> findByEmpId(String empId);
	
	public List<Employee> findAllEmployees();
	
}