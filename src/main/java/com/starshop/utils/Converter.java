package com.starshop.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converter {
	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static LocalDateTime convertToLocalDateTime(String source) {
        return LocalDateTime.parse(source, formatter);
    }
    
	public static String localDateTimeToDateWithSlash(LocalDateTime localDateTime) {
	    return formatter.format(localDateTime);
	}
}
