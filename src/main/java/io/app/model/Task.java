package io.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Task {
	@Id
	private String id;
	private String title;
	private String description;
	private String category;
	private boolean isCompleted;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime scheduleTime;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt=LocalDateTime.now();
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
}







