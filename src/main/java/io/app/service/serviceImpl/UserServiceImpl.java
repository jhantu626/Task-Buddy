package io.app.service.serviceImpl;

import io.app.model.User;
import io.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {
	private final UserRepository userRepository;
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
}
