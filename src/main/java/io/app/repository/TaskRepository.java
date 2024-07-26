package io.app.repository;

import io.app.model.Task;
import io.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends JpaRepository<Task,String> {
	Page<Task> findByUser(User user,Pageable pageable);
	Page<Task> findByTitleContainingAndUser(String title,User user,Pageable pageable);
	Page<Task> findByCategoryAndUser(String category,User user,Pageable pageable);
	Page<Task> findByIsCompletedAndUser(boolean isComplete,User user,Pageable pageable);
}
