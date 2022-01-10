package com.apps.org.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.org.dto.EmployeeDTO;
import com.apps.org.entity.Employee;
import com.apps.org.model.EmployeeAddressRequest;
import com.apps.org.model.EmployeeAddressResponse;
import com.apps.org.service.EmployeeService;

@RestController
@RequestMapping("/rest-api/v1")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/employees")
	public ResponseEntity<List<EmployeeDTO>> addNewEmployees(@RequestBody @Valid List<EmployeeDTO> employeeRequest) {
		List<EmployeeDTO> response = employeeService.addNewEmployees(employeeRequest);
		return new ResponseEntity<List<EmployeeDTO>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> response = employeeService.getAllEmployees();
		return ResponseEntity.ok().body(response);
	}

	@GetMapping(value = "/employees/{empId}", produces = "application/json")
	public ResponseEntity<?> getEmployee(@PathVariable @Valid String empId) {
		Employee employee = employeeService.getEmployee(empId);
		return ResponseEntity.ok().body(employee);
	}

	@PutMapping("/employees")
	public ResponseEntity<List<Employee>> updateEmployees(@RequestBody List<Employee> employeeRequest) {
		List<Employee> response = employeeService.updateEmployees(employeeRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PatchMapping(value = "/employees/{empId}", produces = "application/json")
	public ResponseEntity<EmployeeAddressResponse> updateEmployeeAddress(@PathVariable @Valid String empId,
			@RequestBody @Valid EmployeeAddressRequest employeeAddressRequest) {

		EmployeeAddressResponse response = employeeService.updateEmployeeAddress(empId, employeeAddressRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/employees/{empId}")
	public ResponseEntity<?> deleteEmployee(@PathVariable @Valid String empId) {
		employeeService.deleteEmployee(empId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/employees")
	public ResponseEntity<?> deleteAllEmployees(@RequestBody @Valid List<Employee> employeeRequest) {
		employeeService.deleteEmployees(employeeRequest);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}