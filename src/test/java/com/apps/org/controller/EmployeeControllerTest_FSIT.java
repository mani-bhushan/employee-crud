package com.apps.org.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.apps.org.entity.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * We are using Spring Boot Test to write a simple Full-Stack Integration Test ( FSIT ).
 * 
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest_FSIT  {

	@LocalServerPort
	private int port;

	private URL base;
	
	private List<String> empIdList;

	@Autowired
	private TestRestTemplate template;

	@BeforeEach
    void init(TestInfo info) throws Exception {
		this.base = new URL("http://localhost:" + port + "/rest-api/v1");
		empIdList = new ArrayList<>();
        if (!info.getTags().contains("Exclude")) {
        	addTestEmployee("");
        } 
    }
	
	@AfterEach
    void tearDown() throws Exception {
        if (empIdList.size()>0) {
        	empIdList.forEach(n -> {
        		deleteTestEmployee(n);
        	});
        }
    }

	@Test
	public void test_GetAllEmployees() {
		ResponseEntity<List> response = template.getForEntity(base.toString() + "/employees", List.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	public void test_GetEmployee() {
		String empId = empIdList.stream().findFirst().get();
		ResponseEntity<Employee> response = template.getForEntity(base.toString() + "/employees/" + empId, Employee.class);
		assertThat(response.getBody().getEmpId()).isEqualTo(empIdList.stream().findFirst().get());
	}
	
	@Test
	@Tag("Exclude")
    public void test_AddNewEmployees() throws Exception {
        ResponseEntity<List<Employee>> result = addTestEmployee("test_AddNewEmployees");
        Assertions.assertEquals(201, result.getStatusCodeValue());
        Assertions.assertEquals("test_AddNewEmployees", result.getBody().stream().findFirst().get().getEmpName());
    }
	
	@Test
	@Tag("Exclude")
    public void test_updateEmployees() throws Exception {
        ResponseEntity<List<Employee>> result = addTestEmployee("");
        List<Employee> object = result.getBody();
        object.stream().findFirst().get().setEmpName("test_updateEmployees");
        object.stream().findFirst().get().setCity("test_updateCity");
        
        result = template.exchange(base.toString() + "/employees", 
        		HttpMethod.PUT,
        		new HttpEntity<List<Employee>>(object), 
        		new ParameterizedTypeReference<List<Employee>>() {});
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals("test_updateEmployees", result.getBody().stream().findFirst().get().getEmpName());
        Assertions.assertEquals("test_updateCity", result.getBody().stream().findFirst().get().getCity());
    }
	
	private ResponseEntity<List<Employee>> addTestEmployee(String empName) throws IOException {
		List<Employee> object = getEmployeeList();
		if (StringUtils.isNotBlank(empName)) object.get(0).setEmpName(empName);
        ResponseEntity<List<Employee>> result = template.exchange(base.toString() + "/employees", HttpMethod.POST,
        		new HttpEntity<List<Employee>>(object), 
        		new ParameterizedTypeReference<List<Employee>>() {});
        if (result != null && result.getStatusCodeValue() == 201) {
        	empIdList.add(result.getBody().get(0).getEmpId());
        }
        return result;
    }
	
	private void deleteTestEmployee(String empId) {
		template.delete(base.toString() + "/employees/" + empId);
    }
	
	private List<Employee> getEmployeeList() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = new String(Files.readAllBytes(Paths.get("src/test/resources/JSON/"+"employeeListRequest.JSON")));
		List<Employee> object = objectMapper.readValue(jsonString, new TypeReference<List<Employee>>(){});
		return object;
	}
	
}
