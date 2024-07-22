package io.app.controller;

import io.app.dto.RequestLogin;
import io.app.dto.ResponseToken;
import io.app.model.User;
import io.app.service.AuthService;
import io.app.service.CustomeUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<ResponseToken> register(@RequestBody User user){
		return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
	}
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseToken login(@RequestBody RequestLogin requestLogin) {
		return authService.login(requestLogin);
	}
}
