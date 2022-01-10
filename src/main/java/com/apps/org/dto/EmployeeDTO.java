package com.apps.org.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EmployeeDTO {
	
	private String empId;
	
	@NotNull(message = "Please provide employee name.")
	private String empName;
	
	@NotNull(message = "Please provide employee birth date.")
	private Date birthDate;
	private Date hireDate;
	
	@NotNull(message = "Please provide employee gender.")
	@Size(min = 1, max = 6, message = "Gender cannot be {max} characters.")
	private String gender;
	
	@NotNull(message = "Please provide employee designation.")
	private String designation;

	private String nationality;
	private Boolean isActive;
	
	@NotNull(message = "Please provide addressLine1.")
	private String addressLine1;
	
	private String addressLine2;
	
	@NotNull(message = "Please provide mobile number.")
	@Size(min = 10, max = 13, message = "Mobile number must be 10 digits.")
	private String mobile;
	
	@NotNull(message = "Please provide city name.")
	private String city;
	
	@NotNull(message = "Please provide state name.")
	private String state;
	
	@NotNull(message = "Please provide country name.")
	private String country;

}
