package io.app.dto;

import io.app.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TaskDto {
	private String id;
	private String title;
	private String description;
	private String category;
	private boolean isCompleted;
	private LocalDateTime scheduleTime;
	private LocalDateTime createdAt=LocalDateTime.now();
	private LocalDateTime updatedAt;
}
