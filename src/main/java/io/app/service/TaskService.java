package io.app.service;

import io.app.dto.ApiResponse;
import io.app.dto.TaskDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
	ApiResponse createTask(String token,TaskDto taskDto);
	TaskDto getTaskById(String token,String id);
	Page<TaskDto> usersTask(String token,int page,int size);
	ApiResponse doTaskCompleted(String token,String id);
	ApiResponse updateTask(String token,String id,TaskDto taskDto);
	ApiResponse deleteTask(String token, String id);
	Page<TaskDto> getTaskByTitle(String token, String title,int pageNo,int pageSize);
	Page<TaskDto> getTaskByCategory(String token,String category,int pageNo,int pageSize);
}
