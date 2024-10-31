package com.starshop.configs;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeFormatValidator implements ConstraintValidator<DateTimeFormat, LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public boolean isValid(LocalDateTime date, ConstraintValidatorContext context) {
        // Null dates are considered valid, as it should be checked by @NotNull if required
        return date != null;
    }

    public boolean isValid(String dateString, ConstraintValidatorContext context) {
        try {
            LocalDateTime.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
