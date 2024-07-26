package io.app.controller;

import io.app.dto.ApiResponse;
import io.app.exception.TokenNotFoundException;
import io.app.model.User;
import io.app.service.serviceImpl.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "authenticationBearer")
public class UserController {
	private final UserServiceImpl userService;

	@GetMapping("/profile")
	@ResponseStatus(HttpStatus.OK)
	public User profile(@RequestHeader("Authorization") String token){
		if (token==null){
			throw new TokenNotFoundException("Token not found!");
		}
		return userService.profile(token.substring(7));
	}

	@GetMapping("/getAll")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllUser(){
		return userService.getAllUser();
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id){
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@GetMapping("/role/{role}")
	public ResponseEntity<List<User>> getUsersByRoles(@PathVariable("role") String role){
		return ResponseEntity.ok(userService.getUserByRole(role));
	}

	@PutMapping("/update")
	public ResponseEntity<ApiResponse> updateUser(
			@RequestHeader("Authorization") String authToken,
			@RequestBody User user){
		authToken=authToken.substring(7);
		return ResponseEntity.ok(userService.updateUser(authToken,user));
	}

	@PatchMapping("/update/password")
	public ResponseEntity<ApiResponse> updatePassword(@RequestHeader("Authorization") String authToken,
													  @RequestParam("oldPassword") String oldPassword,
													  @RequestParam("newPassword") String newPassword){
        authToken=authToken.substring(7);
		return ResponseEntity.ok(userService.updatePassword(authToken,oldPassword,newPassword));
	}


	@DeleteMapping("/deleteAccount")
	public ResponseEntity<ApiResponse> deleteAccount(
			@RequestHeader("Authorization") String token
	){
		token=token.substring(7);
		return ResponseEntity.ok(userService.deleteUserAccount(token));
	}

}