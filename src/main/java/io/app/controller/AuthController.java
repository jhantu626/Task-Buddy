package io.app.controller;

import io.app.dto.RequestLogin;
import io.app.dto.ResponseToken;
import io.app.model.User;
import io.app.service.AuthService;
import io.app.service.CustomeUserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
	private final AuthService authService;
	private final AuthenticationManager authenticationManager;

	@PostMapping("register")
	public ResponseEntity<ResponseToken> register(@RequestBody User user,
												  HttpServletRequest request){
		log.info("Request URI ({}) AT [{}]", request.getRequestURI(), new Date().toLocaleString());
		return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
	}
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.OK)
	public ResponseToken login(@RequestBody RequestLogin requestLogin,
							   HttpServletRequest request) {
		log.info("Request URI ({}) AT [{}]", request.getRequestURI(), new Date().toLocaleString());
		return authService.login(requestLogin);
	}
}
