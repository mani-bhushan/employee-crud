package com.apps.org.custom.exceptions.handler;

/**
 * Thrown if an Employee could not be found by id
 *
 * @author Mani Bhushan
 */
public class EmployeeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4926468583005150708L;

	/**
	 * Constructs a <code>UsernameNotFoundException</code> with the specified message.
	 * @param msg the detail message.
	 */
	public EmployeeNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a {@code UsernameNotFoundException} with the specified message and root
	 * cause.
	 * @param msg the detail message.
	 * @param cause root cause
	 */
	public EmployeeNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
