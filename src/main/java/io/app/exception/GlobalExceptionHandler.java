package io.app.exception;

import io.app.dto.ApiResponse;
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

	@ExceptionHandler(TokenNotFoundException.class)
	public Map<String,HttpStatus> handleTokenNotFoundException(Exception ex){
		Map<String,HttpStatus> map = new HashMap<>();
		map.put("Token Not Found",HttpStatus.NOT_FOUND);
		return map;
	}


	@ExceptionHandler(SignatureException.class)
	public String handleSignatureException(SignatureException ex){
		return "Please provide a valid token";
	}

	@ExceptionHandler(MitchMatchException.class)
	public ApiResponse handleMitchMatchException(MitchMatchException ex){
		return ApiResponse.builder()
				.msg(ex.getMessage())
				.status(false)
				.build();
	}



}
