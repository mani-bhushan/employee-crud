package com.apps.org.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.org.entity.Employee;

@Service
public interface EmployeeService {
	
	public List<Employee> addNewEmployees(List<Employee> employeeRequest);
	public List<Employee> getAllEmployees();
	public Employee getEmployee(Long id);
	public List<Employee> updateEmployees(List<Employee> employeeRequest);
	public void deleteEmployee(Long empId);
	public void deleteEmployees(List<Employee> employeeList);

}
