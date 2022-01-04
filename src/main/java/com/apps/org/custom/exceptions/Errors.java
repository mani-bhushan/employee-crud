package com.apps.org.custom.exceptions;

/**
 * Used to customize an exception to a user-centric error response.
 *
 * @author Mani Bhushan
 */
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Errors {
	
	@NotNull
	private final Integer errorCode;

	@NotNull
	private final String message;	
	
}