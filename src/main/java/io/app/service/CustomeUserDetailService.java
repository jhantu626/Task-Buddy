package io.app.service;

import io.app.exception.ResourceNotFoundException;
import io.app.model.User;
import io.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomeUserDetailService implements UserDetailsService {
	private final UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByEmail(username)
				.orElseThrow(()->new ResourceNotFoundException("ResourceNotFoundException"));
		return user;
	}
}
