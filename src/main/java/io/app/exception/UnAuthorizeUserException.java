package io.app.exception;

public class UnAuthorizeUserException extends RuntimeException{
	public UnAuthorizeUserException(String msg){
		super(msg);
	}
}
