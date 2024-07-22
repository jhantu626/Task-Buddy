package io.app.service.serviceImpl;

import io.app.dto.RequestLogin;
import io.app.dto.ResponseToken;
import io.app.exception.ResourceNotFoundException;
import io.app.model.User;
import io.app.repository.UserRepository;
import io.app.service.AuthService;
import io.app.service.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	@Override
	public ResponseToken register(User user) {
		String uid= UUID.randomUUID().toString();
		user.setId(uid);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return ResponseToken.builder()
				.token(jwtService.generateToken(user))
				.build();
	}

	@Override
	public ResponseToken login(RequestLogin requestLogin) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						requestLogin.getEmail(),
						requestLogin.getPassword()
				)
		);
		User user =userRepository.findByEmail(requestLogin.getEmail())
				.orElseThrow(()->new ResourceNotFoundException("Not Found By email: "+requestLogin.getEmail()));
		String token=jwtService.generateToken(user);
		return ResponseToken.builder().token(token)
				.build();
	}
}
