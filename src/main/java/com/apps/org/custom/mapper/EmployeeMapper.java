package com.apps.org.custom.mapper;

import java.util.Date;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.apps.org.custom.annotation.DesignationMapper;
import com.apps.org.dto.EmployeeDTO;
import com.apps.org.entity.Employee;
import com.apps.org.model.EmployeeAddressRequest;
import com.apps.org.model.EmployeeAddressResponse;

@Mapper(
		uses = {MyDateMapper.class},
		componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

	@Named("genderFullForm")
	public static String toFullForm(String value) {
		return (value.equals("M")) ? "Male" : "Female";
	}
	
	@Named("stringToNull")
	public static String stringToNull(String value) {
		return null;
	}
	
	@Named("booleanToNull")
	public static Boolean booleanToNull(Boolean value) {
		return null;
	}
	
	@Named("dateToNull")
	public static Date dateToNull(Date value) {
		return null;
	}

	@DesignationMapper
	public static String designationMapper(String designation) {
		return designation + " @com.apps.org";
	}

	
	@Mapping(target = "hireDate", qualifiedByName = "dateToNull")
	@Mapping(target = "gender", source = "emp.gender", qualifiedByName = "genderFullForm")
	@Mapping(target = "designation", source = "emp.designation", qualifiedBy = DesignationMapper.class)
	@Mapping(target = "nationality", qualifiedByName = "stringToNull")
	@Mapping(target = "isActive", qualifiedByName = "booleanToNull")
	@Mapping(target = "addressLine2", qualifiedByName = "stringToNull")
	@Mapping(target = "country", qualifiedByName = "stringToNull")
	EmployeeDTO convertEmployeeEntityToEmployeeDTO(Employee emp);
	
	@Mappings({
		@Mapping(target = "empId", qualifiedByName = "stringToNull")
	})
	Employee convertEmployeeDTOToAddEmployeeEntity(EmployeeDTO emp);
	
	Employee convertEmployeeAddressRequestToEmployeeEntity(EmployeeAddressRequest req, @MappingTarget Employee emp);

	EmployeeAddressResponse convertEmployeeEntityToEmployeeAddressResponse(Employee employee);

}
