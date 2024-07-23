package io.app.service.serviceImpl;

import io.app.dto.ApiResponse;
import io.app.exception.MitchMatchException;
import io.app.exception.ResourceNotFoundException;
import io.app.model.User;
import io.app.repository.UserRepository;
import io.app.service.JwtService;
import io.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User profile(String token) {
		String email=jwtService.extractUsername(token);
		User user=userRepository.findByEmail(email)
				.orElseThrow(()->new ResourceNotFoundException("Resource Not Found By Email: "+email));
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user=userRepository.findByEmail(email)
				.orElseThrow(()->new ResourceNotFoundException("Resource Not Found By Email: "+email));
		return user;
	}

	public List<User> getAllUser(){
		return userRepository.findAll();
	}

	@Override
	public User getUserById(String id) {
		User user=userRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resource Not Found By Id: "+id));
		return null;
	}

	@Override
	public List<User> getUserByRole(String role) {
		List<User> users=userRepository.findByRoles(role);
		return users;
	}

	@Override
	public ApiResponse updateUser(String token, User user) {
		String email=jwtService.extractUsername(token);
		User databaseUser=userRepository.findByEmail(email)
				.orElseThrow(()->new ResourceNotFoundException("ResourceNot Found by token, provide a valid token!"));
		databaseUser.setFirstName(user.getFirstName());
		databaseUser.setLastName(user.getLastName());
		databaseUser.setRoles(user.getRoles()!=null?user.getRoles():databaseUser.getRoles());
		userRepository.save(databaseUser);
		return ApiResponse.builder()
				.msg("Successfully User data updated!")
				.status(true).build();
	}

	@Override
	public ApiResponse updatePassword(String token, String oldPassword, String newPassword) {
		String email=jwtService.extractUsername(token);
		User user=userRepository.findByEmail(email)
				.orElseThrow(()->new ResourceNotFoundException("ResourceNot Found by token, provide a valid token!"));
		if (!passwordEncoder.matches(oldPassword,user.getPassword())) {
			throw new MitchMatchException("Password is not matching!");
		}
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
		return ApiResponse.builder()
				.msg("Successfully password updated!")
				.status(true)
				.build();
	}

	@Override
	public ApiResponse deleteUserAccount(String token) {
		String email=jwtService.extractUsername(token);
		User user=userRepository.findByEmail(email)
				.orElseThrow(()->new ResourceNotFoundException("User does not exist!"));
		userRepository.delete(user);
		return ApiResponse.builder()
				.msg("Account Deleted Successfully!")
				.status(true)
				.build();
	}


}