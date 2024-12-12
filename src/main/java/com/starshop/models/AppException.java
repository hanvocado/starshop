package com.starshop.models;
import lombok.*;

@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private int code;
    private String message;
}