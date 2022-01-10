package com.apps.org.custom.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class MyDateMapper {
	private final String THE_FORMAT = "YYYY-MM-dd HH:mm";
	private final DateTimeFormatter DATETIMEFORMATTER = DateTimeFormatter.ofPattern(THE_FORMAT);

	public LocalDateTime asLocalDateTime(String str) {
		return LocalDateTime.parse(str, DATETIMEFORMATTER);
	}

	public String asString(LocalDateTime datetime) {
		return datetime.format(DATETIMEFORMATTER);
	}
	
	/*
	 * public Date stringAsUtildate(String str) { return null; //
	 * LocalDateTime.parse(str, DATETIMEFORMATTER); }
	 * 
	 * public String utilDateasString(Date date) { return null;
	 * //date.format(DATETIMEFORMATTER); }
	 */
}
