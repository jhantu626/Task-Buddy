package io.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public Map<HttpStatus,String> handleResourceNotFoundException(
			ResourceNotFoundException ex
	){
		HashMap<HttpStatus,String> map = new HashMap<>();
		map.put(HttpStatus.NOT_FOUND,ex.getMessage());
		return map;
	}

	@ExceptionHandler(SignatureException.class)
	public String handleSignatureException(SignatureException ex){
		return "Please provide a valid token";
	}



}
