package com.task.Task.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.Task.entity.Task;
import com.task.Task.entity.User;
import com.task.Task.exception.TaskNotFoundException;
import com.task.Task.repository.TaskRepository;

@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	@Lazy
	private UserService userService;

	public Task save(Task task, String username) {
		
		Task saved = null;
		User user = userService.getByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username does not exists");
		}

		task.setUser(user);
			saved = taskRepo.save(task);

		return saved;

	}

	public Iterable<Task> getTaskByUsername(String username) {
		User user = userService.getByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username does not exists");
		}

		Iterable<Task> tasks = taskRepo.findByUser(user).orElse(null);

		return tasks;
	}

	public Task getTaskByUsernameAndId(String username, Long id) {
		User user = userService.getByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username does not exists");
		}

		Task task = taskRepo.findByUserAndId(user, id).orElse(null);

		if (task == null) {
			 throw new TaskNotFoundException("Task with id:"+id+" does not exist");
		}
		
		
		return task;

	}
	
	public boolean deleteTask(String username, Long id) {
		Task task = getTaskByUsernameAndId(username, id);
		taskRepo.delete(task);
		
		return true;
	}

	public Iterable<Task> saveAll(Iterable<Task> tasks, String username) {
			
		User user = userService.getByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username does not exists");
		}

		for (Task task : tasks) {
			task.setUser(user);
		}
		Iterable<Task> saved = taskRepo.saveAll(tasks);

		return saved;

	}
}
