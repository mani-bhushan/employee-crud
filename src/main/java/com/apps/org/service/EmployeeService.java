package com.apps.org.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.org.entity.Employee;

@Service
public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	public List<Employee> addNewEmployee(List<Employee> employeeRequest);
	public Employee getEmployee(Long id);
	public List<Employee> updateEmployee(List<Employee> employeeRequest);
	public void deleteEmployee(Long empId);
	public void deleteEmployee(List<Employee> employeeList);

}
