package io.app.service;

import io.app.dto.ApiResponse;
import io.app.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {
	User profile(String token);
	User getUserByEmail(String email);
	List<User> getAllUser();
	User getUserById(String id);
	List<User> getUserByRole(String role);
	ApiResponse updateUser(String token,User user);
	ApiResponse updatePassword(String token,String oldPassword,String newPassword);
	ApiResponse deleteUserAccount(String token);
}