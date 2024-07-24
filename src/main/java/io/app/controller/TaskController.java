package io.app.controller;

import io.app.dto.ApiResponse;
import io.app.dto.TaskDto;
import io.app.service.serviceImpl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
	private final TaskServiceImpl service;


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse createTask(
			@RequestHeader("Authorization") String token,
			@RequestBody TaskDto taskDto){
		token=token.substring(7);
		return service.createTask(token,taskDto);
	}


	@GetMapping
	public ResponseEntity<Page<TaskDto>> usersTasks(
			@RequestHeader("Authorization") String token,
			@RequestParam(required = false,defaultValue = "0") int page,
			@RequestParam(required = false,defaultValue = "10") int size){
		token=token.substring(7);
		return ResponseEntity.ok(service.usersTask(token,page,size));
	}


	@PatchMapping("/do-task-complete/{taskId}")
	public ResponseEntity<ApiResponse> doTaskComplete(
			@PathVariable String taskId
	){
		return ResponseEntity.ok(service.doTaskCompleted(taskId));
	}



}
