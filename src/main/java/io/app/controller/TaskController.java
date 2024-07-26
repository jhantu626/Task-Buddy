package io.app.controller;

import io.app.dto.ApiResponse;
import io.app.dto.TaskDto;
import io.app.service.serviceImpl.TaskServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/task")
@RequiredArgsConstructor
@SecurityRequirement(name = "authenticationBearer")
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


	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TaskDto getTaskById(
			@RequestHeader("Authorization") String token,
			@PathVariable String id){
		token=token.substring(7);
		return service.getTaskById(token,id);
	}


	@GetMapping
	public ResponseEntity<Page<TaskDto>> usersTasks(
			@RequestHeader("Authorization") String token,
			@RequestParam(required = false,defaultValue = "0") int page,
			@RequestParam(required = false,defaultValue = "10") int size,
			@RequestParam(required = false, defaultValue = "true") boolean ascending){
		token=token.substring(7);
		return ResponseEntity.ok(service.usersTask(token,page,size,ascending));
	}


	@PatchMapping("/do-task-complete/{taskId}")
	public ResponseEntity<ApiResponse> doTaskComplete(
			@RequestHeader("Authorization") String token,
			@PathVariable String taskId
	){
		token=token.substring(7);
		return ResponseEntity.ok(service.doTaskCompleted(token,taskId));
	}

	@PutMapping("/update-task/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ApiResponse updateTask(
			@RequestHeader("Authorization") String token,
			@PathVariable String id,
			@RequestBody TaskDto taskDto){
		token=token.substring(7);
		return service.updateTask(token,id,taskDto);
	}

	@DeleteMapping("delete-task/{id}")
	public ApiResponse delteTask(
			@RequestHeader("Authorization") String token,
			@PathVariable String id){
		token=token.substring(7);
		return service.deleteTask(token,id);
	}

	@GetMapping("/get-task-by-title")
	public Page<TaskDto> getTaskByTitile(
			@RequestHeader("Authorization") String token,
			@RequestParam(name = "title") String title,
			@RequestParam(required = false, defaultValue = "0") int pageNo,
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			@RequestParam(required = false,defaultValue = "true") boolean ascending
	){
		token=token.substring(7);
		return service.getTaskByTitle(token,title,pageNo,pageSize,ascending);
	}

	@GetMapping("/get-task-by-category")
	@ResponseStatus(HttpStatus.OK)
	public Page<TaskDto> getTaskByCategory(
			@RequestHeader("Authorization") String token,
			@RequestParam(name = "category") String category,
			@RequestParam(required = false, defaultValue = "0") int pageNo,
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			@RequestParam(required = false,defaultValue = "true") boolean ascending
	){
		token=token.substring(7);
		return service.getTaskByCategory(token,category,pageNo,pageSize,ascending);
	}

	@GetMapping("/task-by-complete")
	@ResponseStatus(HttpStatus.OK)
	public Page<TaskDto> getTaskByIsCompleted(
			@RequestHeader("Authorization") String token,
			@RequestParam(name = "complete-task",defaultValue = "false",required = false)
			boolean isCompleted,
			@RequestParam(name = "pageNo",required = false,
					defaultValue = "0") int pageNo,
			@RequestParam(name = "pageSize",required = false,
			defaultValue = "10") int pageSize,
			@RequestParam(required = false,defaultValue = "true") boolean ascending){
		token=token.substring(7);
		return service.getCompleteAndInCompleteTask(token,isCompleted,pageNo,pageSize,ascending);
	}

}
