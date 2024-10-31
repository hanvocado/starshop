package com.starshop.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
	public static String localDateTimeToDateWithSlash(LocalDateTime localDateTime) {
	    return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(localDateTime);
	}
}
