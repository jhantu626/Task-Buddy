package io.app.service.serviceImpl;

import io.app.dto.ApiResponse;
import io.app.dto.TaskDto;
import io.app.exception.ResourceNotFoundException;
import io.app.model.Task;
import io.app.model.User;
import io.app.repository.TaskRepository;
import io.app.service.JwtService;
import io.app.service.TaskService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
	private final TaskRepository repository;
	private final ModelMapper modelMapper;
	private final JwtService jwtService;
	private final UserServiceImpl userService;


	@Override
	public ApiResponse createTask(String token,TaskDto taskDto) {
		String userEmail=jwtService.extractUsername(token);
		User user= userService.getUserByEmail(userEmail);
		Task task= modelMapper.map(taskDto,Task.class);
		task.setId(UUID.randomUUID().toString());
		task.setUpdatedAt(LocalDateTime.now());
		task.setUser(user);
		repository.save(task);
		return ApiResponse.builder()
				.msg("Task Added Successfully!")
				.status(true)
				.build();
	}

	@Override
	public Page<TaskDto> usersTask(String token,int page,int size) {
		String email=jwtService.extractUsername(token);
		User user=userService.getUserByEmail(email);
		Pageable pageable = PageRequest.of(page,size);
		Page<Task> res=repository.findByUser(user,pageable);
		List<TaskDto> finalRes=res.stream()
				.map((task -> modelMapper.map(task,TaskDto.class)))
				.collect(Collectors.toList());
		Page<TaskDto> resPage=new PageImpl<>(finalRes,pageable,finalRes.size());
		return resPage;
	}

	@Override
	public ApiResponse doTaskCompleted(String id) {
		Task task=repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Invalid Task Id!"));
		task.setCompleted(true);
		repository.save(task);
		return ApiResponse.builder()
				.msg("Task Completed Successfully!")
				.status(true)
				.build();
	}

}
