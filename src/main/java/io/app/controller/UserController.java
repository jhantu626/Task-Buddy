package io.app.controller;

import io.app.model.User;
import io.app.service.UserService;
import io.app.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserServiceImpl userService;

	@GetMapping("/getAll")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
}
