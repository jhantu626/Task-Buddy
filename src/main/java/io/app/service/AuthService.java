package io.app.service;

import io.app.dto.RequestLogin;
import io.app.dto.ResponseToken;
import io.app.model.User;

public interface AuthService {
	public ResponseToken register(User user);
	public ResponseToken login(RequestLogin requestLogin);
}
