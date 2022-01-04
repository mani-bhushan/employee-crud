package com.apps.org.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "employee", uniqueConstraints = { @UniqueConstraint(name = "UniqueEmployeeKey", columnNames = { "empName", "gender", "mobile", "isActive" }) })
public class Employee implements Serializable {

	private static final long serialVersionUID = 4926468583005150707L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long empId;
	
	@NotNull
	private String empName;
	
	@NotNull
	private Date birthDate;
	
	@NotNull
	private Date hireDate;	 
		
	@NotNull
	private String gender;
	
	@NotNull
	private String designation;
	
	@NotNull
	private String nationality;
	
	@NotNull
	private Boolean isActive;
	
	@NotNull
	private String addressLine1;
	
	@NotNull
	private String addressLine2;

	@NotNull
	@Size(min = 10, max = 10, message="{Mobile number must be 10 digits.}")
	private String mobile;
	
	@NotNull
	private String city;
	
	@NotNull
	private String state;
	
	@NotNull
	private String country;
}
