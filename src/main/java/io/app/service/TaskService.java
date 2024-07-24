package io.app.service;

import io.app.dto.ApiResponse;
import io.app.dto.TaskDto;
import org.springframework.data.domain.Page;

public interface TaskService {
	ApiResponse createTask(String token,TaskDto taskDto);
	Page<TaskDto> usersTask(String token,int page,int size);
	ApiResponse doTaskCompleted(String id);
}
