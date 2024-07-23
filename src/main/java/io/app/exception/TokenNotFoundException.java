package io.app.exception;

public class TokenNotFoundException extends RuntimeException {
	public TokenNotFoundException(String msg) {
		super(msg);
	}
}
