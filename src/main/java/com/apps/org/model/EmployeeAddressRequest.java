package com.apps.org.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class EmployeeAddressRequest {

	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;

}
