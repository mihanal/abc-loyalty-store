package com.abc.ecom.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {

	private int statusCode;
	private LocalDateTime dateTime;
	private String message;
}
