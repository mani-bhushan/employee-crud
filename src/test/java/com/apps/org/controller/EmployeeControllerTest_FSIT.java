package com.apps.org.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.apps.org.entity.Employee;

/**
 * Mocking the HTTP request cycle 
 * We are using Spring Boot to write a simple Full-Stack Integration Test ( FSIT ).
 * 
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest_FSIT {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate template;

	@BeforeEach
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}

	@Test
	public void test_GetAllEmployees() {
		ResponseEntity<List> response = template.getForEntity(base.toString() + "rest-api/v1/employees", List.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void test_GetEmployee() {
		ResponseEntity<Employee> response = template.getForEntity(base.toString() + "rest-api/v1/employees/1",
				Employee.class);
		assertThat(response.getBody().getEmpId()).isEqualTo("EMP_00001");
	}

}
