package com.apps.org.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonPropertyOrder({"empId", "empName"})
@JsonInclude(Include.NON_NULL)
public class EmployeeAddressResponse extends EmployeeAddressRequest {

	private Long empId;
	private String empName;

}
