package com.apps.org.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
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
	public List<Employee> addNewEmployees(List<Employee> employeeRequest) {
		List<Employee> employeeResp = new ArrayList<Employee>();
		employeeRequest.forEach(emp -> {
			if (emp.getEmpId() != null) {
				/* 
				 * If request already have empId it will act as an update employee
				 * we can throw an exception or proceed according to the requirement. 
				 * Here to keep it stick on a create request setting empId to null.
				 * 
				 * */
				emp.setEmpId(null); 
			}
			employeeResp.add(repository.save(emp));
		});
		return employeeResp;
	}
	
	@Override
	public List<Employee> getAllEmployees() {

		return IterableUtils.toList(repository.findAll());   
	}

	@Override
	public Employee getEmployee(Long id) {
		return repository
				.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Please provide a valid Employee ID: " +  String.valueOf(id)));
	}

	@Override
	@Transactional
	public List<Employee> updateEmployees(List<Employee> employeeRequest) {
		List<Employee> employeeResp = new ArrayList<Employee>();
		employeeRequest.forEach(emp -> {
			if (emp.getEmpId() == null) { 
				throw new NullPointerException("Please provide Employee ID.");
			} else if (!(repository.findById(emp.getEmpId()).isPresent())) {
				throw new EmployeeNotFoundException("Please provide a valid Employee ID: " + String.valueOf(emp.getEmpId()));
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
			throw new EmployeeNotFoundException("Please provide a valid Employee ID: " +  String.valueOf(id));
		} else {
			repository.delete(employee.get());
		}
	}

	@Override
	@Transactional
	public void deleteEmployees(List<Employee> employeeList) {
		
		employeeList.forEach(emp -> {
			if (emp.getEmpId() == null) {
				throw new NullPointerException("Please provide Employee ID.");
			} else if (!(repository.findById(emp.getEmpId()).isPresent())) {
				throw new EmployeeNotFoundException("Please provide a valid Employee ID: " + String.valueOf(emp.getEmpId()));
			}
		});
		
		repository.deleteAll(employeeList);
	}



}
