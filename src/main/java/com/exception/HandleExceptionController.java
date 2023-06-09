package com.exception;

import javax.mail.MessagingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleExceptionController {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> generalException(MessagingException e) {
		ErrorResponse res = new ErrorResponse();
		res.setStatus(HttpStatus.BAD_REQUEST.value());
		res.setMessage("Cann't send email");
		res.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(res, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> generalException(Exception e) {
		ErrorResponse res = new ErrorResponse();
		res.setStatus(HttpStatus.BAD_REQUEST.value());
		res.setMessage(e.getMessage());
		res.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorResponse>(res, HttpStatus.BAD_REQUEST);
	}
}
