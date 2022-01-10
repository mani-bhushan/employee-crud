package com.apps.org.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.org.custom.exceptions.handler.EmployeeNotFoundException;
import com.apps.org.custom.mapper.EmployeeMapper;
import com.apps.org.dao.repositories.EmployeeRepository;
import com.apps.org.dto.EmployeeDTO;
import com.apps.org.entity.Employee;
import com.apps.org.model.EmployeeAddressRequest;
import com.apps.org.model.EmployeeAddressResponse;
import com.apps.org.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private EmployeeMapper employeeMapper;

	@Transactional
	public List<EmployeeDTO> addNewEmployees(List<EmployeeDTO> employeeRequest) {
		List<EmployeeDTO> employeeResp = new ArrayList<EmployeeDTO>(employeeRequest.size());
		employeeRequest.forEach(dto -> {
			Employee emp = employeeMapper.convertEmployeeDTOToAddEmployeeEntity(dto);
			emp = repository.save(emp);
			employeeResp.add(employeeMapper.convertEmployeeEntityToEmployeeDTO(emp));
		});
		return employeeResp;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return IterableUtils.toList(repository.findAll());
	}

	@Override
	public Employee getEmployee(String empId) {
		Optional<Employee> employee = repository.findByEmpId(empId);
		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException("Please provide a valid Employee ID: " + empId);
		} else {
			return employee.get();
		}
	}

	@Override
	@Transactional
	public List<Employee> updateEmployees(List<Employee> employeeRequest) {
		List<Employee> employeeResp = new ArrayList<Employee>();
		employeeRequest.forEach(emp -> {
			if (emp.getEmpId() == null) {
				throw new NullPointerException("Please provide Employee ID.");
			} else if (!(repository.findByEmpId(emp.getEmpId()).isPresent())) {
				throw new EmployeeNotFoundException("Please provide a valid Employee ID: " + emp.getEmpId());
			} else {
				employeeResp.add(repository.save(emp));
			}
		});
		return employeeResp;
	}

	@Override
	@Transactional
	public void deleteEmployee(String empId) {

		Optional<Employee> employee = repository.findByEmpId(empId);
		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException("Please provide a valid Employee ID: " + empId);
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
			} else if (!(repository.findByEmpId(emp.getEmpId()).isPresent())) {
				throw new EmployeeNotFoundException("Please provide a valid Employee ID: " + emp.getEmpId());
			}
		});
		repository.deleteAll(employeeList);
	}

	@Override
	public EmployeeAddressResponse updateEmployeeAddress(String empId, EmployeeAddressRequest employeeAddressRequest) {

		Optional<Employee> employee = repository.findByEmpId(empId);
		if (employee.isPresent()) {
			employeeMapper.convertEmployeeAddressRequestToEmployeeEntity(employeeAddressRequest, employee.get());
			repository.save(employee.get());
			return employeeMapper.convertEmployeeEntityToEmployeeAddressResponse(employee.get());
		} else {
			throw new EmployeeNotFoundException("Please provide a valid Employee ID: " + empId);
		}
	}
	
}
