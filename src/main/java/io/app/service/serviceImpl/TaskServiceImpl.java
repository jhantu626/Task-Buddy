package io.app.service.serviceImpl;

import io.app.dto.ApiResponse;
import io.app.dto.TaskDto;
import io.app.exception.ResourceNotFoundException;
import io.app.exception.UnAuthorizeUserException;
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
	public TaskDto getTaskById(String token, String id) {
		String email=jwtService.extractUsername(token);
		Task task=repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resource Not Found!"));
		if (!task.getUser().getEmail().equals(email)){
			throw new UnAuthorizeUserException("UnAuthorized User!");
		}
		TaskDto taskDto=modelMapper.map(task,TaskDto.class);
		return taskDto;
	}

	@Override
	public Page<TaskDto> usersTask(String token,int page,int size) {
		String email=jwtService.extractUsername(token);
		User user=userService.getUserByEmail(email);
		Pageable pageable = PageRequest.of(page,size);
		List<Task> res=repository.findByUser(user);
		List<TaskDto> finalRes=res.stream()
				.map((task -> modelMapper.map(task,TaskDto.class)))
				.collect(Collectors.toList());
		Page<TaskDto> resPage=new PageImpl<>(finalRes,pageable,finalRes.size());
		return resPage;
	}

	@Override
	public ApiResponse doTaskCompleted(String token,String id) {
		Task task=repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Invalid Task Id!"));

		String userEmail=jwtService.extractUsername(token);
		if (!task.getUser().getEmail().equals(userEmail)){
			throw new UnAuthorizeUserException("UnAuthorized User!");
		}

		task.setCompleted(true);
		repository.save(task);
		return ApiResponse.builder()
				.msg("Task Completed Successfully!")
				.status(true)
				.build();
	}

	@Override
	public ApiResponse updateTask(String token, String id, TaskDto taskDto) {
		String userEmail=jwtService.extractUsername(token);
		Task task=repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resource Not Found!"));
		if (!task.getUser().getEmail().equals(userEmail)){
			throw new UnAuthorizeUserException("UnAuthorized User!");
		}
		task.setTitle(taskDto.getTitle()!=null?taskDto.getTitle():task.getTitle());
		task.setDescription(taskDto.getDescription()!=null?taskDto.getDescription():task.getDescription());
		task.setCategory(taskDto.getCategory()!=null?taskDto.getCategory():task.getCategory());
		task.setScheduleTime(taskDto.getScheduleTime()!=null?taskDto.getScheduleTime():task.getScheduleTime());
		task.setUpdatedAt(LocalDateTime.now());
		repository.save(task);
		return ApiResponse.builder()
				.msg("Task updated successfully!")
				.status(true)
				.build();
	}

	@Override
	public ApiResponse deleteTask(String token, String id) {
		String userEmail=jwtService.extractUsername(token);
		Task task=repository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Resource Not Found!"));
		if (!task.getUser().getEmail().equals(userEmail)){
			throw new UnAuthorizeUserException("UnAuthorized user!");
		}
		repository.delete(task);
		return ApiResponse.builder()
				.msg("Task Deleted Successfully!")
				.status(true)
				.build();
	}

	@Override
	public Page<TaskDto> getTaskByTitle(String token, String title,
										int pageNo,int pageSize) {
		String email=jwtService.extractUsername(token);
		User user=userService.getUserByEmail(email);
		List<Task> tasks=repository.findByTitleContainingAndUser(title,user);
		List<TaskDto> taskDtos=tasks.parallelStream().map((task)->modelMapper.map(task, TaskDto.class))
				.collect(Collectors.toList());
		Pageable pageable=PageRequest.of(pageNo,pageSize, Sort.by("scheduleTime")
				.ascending());
		Page<TaskDto> res=new PageImpl<>(taskDtos,pageable,taskDtos.size());
		return res;
	}

	@Override
	public Page<TaskDto> getTaskByCategory(String token, String category,
										   int pageNo,int pageSize) {
		String email=jwtService.extractUsername(token);
		User user=userService.getUserByEmail(email);
		List<Task> tasks=repository.findByCategoryAndUser(category,user);
		List<TaskDto> taskDtos=tasks.stream().map((task)->modelMapper.map(task,TaskDto.class))
				.collect(Collectors.toList());
		Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by("scheduleTime")
				.ascending());
		//Converting List<TaskDto> to Page<TaskDto>
		Page<TaskDto> page = new PageImpl<>(taskDtos,pageable,taskDtos.size());
		return page;
	}


}
