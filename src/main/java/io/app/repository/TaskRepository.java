package io.app.repository;

import io.app.model.Task;
import io.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,String> {
	List<Task> findByUser(User user);
	List<Task> findByTitleContainingAndUser(String title,User user);
	List<Task> findByCategoryAndUser(String category,User user);
}
