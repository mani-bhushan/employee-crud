package com.apps.org.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 4926468583005150707L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "emp_id", unique=true, nullable=false)
	private Long empId;
	
	@NotNull
	@Column(name = "emp_name", nullable=false)
	private String empName;
	
	@NotNull
	@Column(name = "birth_date", nullable=false)
	private Date birthDate;
	
	@NotNull
	@Column(name = "hire_date", nullable=false)
	private Date hireDate;	 
		
	@NotNull
	@Column(name = "gender", nullable=false)
	private String gender;
	
	@NotNull
	@Column(name = "designation", nullable=false)
	private String designation;
	
	@NotNull
	@Column(name = "nationality", nullable=false)
	private String nationality;
	
	@NotNull
	@Column(name = "is_active", nullable=false)
	private Boolean isActive;
	
	@NotNull
	@Column(name = "address_Line1", nullable=false)
	private String addressLine1;
	
	@NotNull
	@Column(name = "address_Line2", nullable=false)
	private String addressLine2;

	@NotNull
	@Column(name = "mobile", nullable=false)
	private String mobile;
	
	@NotNull
	@Column(name = "city", nullable=false)
	private String city;
	
	@NotNull
	@Column(name = "state", nullable=false)
	private String state;
	
	@NotNull
	@Column(name = "country", nullable=false)
	private String country;
}
