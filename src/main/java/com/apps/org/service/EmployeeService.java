package com.apps.org.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.apps.org.dto.EmployeeDTO;
import com.apps.org.entity.Employee;
import com.apps.org.model.EmployeeAddressRequest;
import com.apps.org.model.EmployeeAddressResponse;

@Service
public interface EmployeeService {
	
	public List<EmployeeDTO> addNewEmployees(List<EmployeeDTO> employeeRequest);
	public List<Employee> getAllEmployees();
	public Employee getEmployee(String empId);
	public List<Employee> updateEmployees(List<Employee> employeeRequest);
	public void deleteEmployee(String empId);
	public void deleteEmployees(List<Employee> employeeList);
	public EmployeeAddressResponse updateEmployeeAddress(String empId, EmployeeAddressRequest employeeAddressRequest);

}
