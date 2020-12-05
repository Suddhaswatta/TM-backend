package com.task.Task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.Task.entity.Task;
import com.task.Task.entity.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	Optional<Iterable<Task>> findByUser(User user);

	Optional<Task> findByUserAndId(User user, Long id);

}
