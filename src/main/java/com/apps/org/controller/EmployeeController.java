package com.apps.org.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.org.entity.Employee;
import com.apps.org.service.EmployeeService;

@CrossOrigin
@RestController
@RequestMapping("/rest-api/v1")
public class EmployeeController {


	@Autowired
	EmployeeService employeeService;

	@PostMapping("/employees")
	public ResponseEntity<List<Employee>> addNewEmployee (
			@RequestBody List<Employee> employeeRequest) {
		
		List<Employee> response = employeeService.addNewEmployee(employeeRequest);
		return new ResponseEntity<List<Employee>>(response, HttpStatus.CREATED);

	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> response = employeeService.getAllEmployees();
		return ResponseEntity.ok().body(response);
	}	
	
	@GetMapping(value="/employees/{id}", produces="application/json")
	public ResponseEntity<?> getEmployee(@PathVariable @Valid Long id) {
			Employee employee = employeeService.getEmployee(id);
			return ResponseEntity.ok().body(employee);
	}


	@PutMapping("/employees")
	public ResponseEntity<List<Employee>> updateeEmployee(@RequestBody List<Employee> employeeRequest) {
		List<Employee> response = employeeService.updateEmployee(employeeRequest);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable @Valid Long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/employees")
	public ResponseEntity<?> deleteAllEmployees(@RequestBody List<Employee> employeeRequest) {
		employeeService.deleteEmployee(employeeRequest);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}