package com.apps.org.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.org.custom.exceptions.handler.EmployeeNotFoundException;
import com.apps.org.dao.repositories.EmployeeRepository;
import com.apps.org.entity.Employee;
import com.apps.org.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Transactional
	public List<Employee> addNewEmployee(List<Employee> employeeRequest) {
		List<Employee> employeeResp = new ArrayList<Employee>();
		employeeRequest.forEach(emp -> {
			if (emp.getEmpId() != null) { 
				emp.setEmpId(null);
			} 
			employeeResp.add(repository.save(emp));
		});
		return employeeResp;
	}
	
	@Override
	public List<Employee> getAllEmployees() {

		return (List<Employee>) repository.findAll();
	}

	@Override
	public Employee getEmployee(Long id) {

		return repository
				.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Please provide a valid Employee ID: " +  String.valueOf(id)));
	}

	@Override
	@Transactional
	public List<Employee> updateEmployee(List<Employee> employeeRequest) {
		List<Employee> employeeResp = new ArrayList<Employee>();
		employeeRequest.forEach(emp -> {
			if (emp.getEmpId() == null) { 
				throw new NullPointerException("Please provide Employee ID.");
			} else if (!(repository.findById(emp.getEmpId()).isPresent())) {
				throw new EmployeeNotFoundException("Please provide a valid Employee ID.");
			} else {
				employeeResp.add(repository.save(emp));
			}
		});
		return employeeResp;
	}


	@Override
	@Transactional
	public void deleteEmployee(Long id) {
		
		Optional<Employee> employee = repository.findById(id);
		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException("Please provide a valid Employee ID.");
		} else {
			repository.delete(employee.get());
		}
	}

	@Override
	@Transactional
	public void deleteEmployee(List<Employee> employeeList) {
		
		employeeList.forEach(emp -> {
			if (emp.getEmpId() == null) { 
				throw new NullPointerException("Please provide Employee ID.");
			} else if (!(repository.findById(emp.getEmpId()).isPresent())) {
				throw new EmployeeNotFoundException("Please provide a valid Employee ID.");
			} 
		});
		
		repository.deleteAll(employeeList);
	}



}
