package com.task.Task.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.Task.entity.Task;
import com.task.Task.service.TaskService;

@RestController
@RequestMapping("api/v1")
public class TaskController {

	@Autowired
	private TaskService taskservice;

	@GetMapping("/logout")
	public ResponseEntity<?> logout(Principal principal) {
		SecurityContextHolder.clearContext();
		return new ResponseEntity<>(HttpStatus.OK);
	} 


	@GetMapping("/tasks")
	public ResponseEntity<?> tasks(Principal principal) {

		Iterable<Task> tasks = taskservice.getTaskByUsername(principal.getName().toLowerCase());

		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/tasks/{id}")
	public ResponseEntity<?> tasks(Principal principal,@PathVariable Long id) {

		Task tasks = taskservice.getTaskByUsernameAndId(principal.getName().toLowerCase(),id);

		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@PostMapping("/tasks")
	public ResponseEntity<?> save(@RequestBody Task task, Principal principal) {
		
		Task saved = taskservice.save(task, principal.getName().toLowerCase());

		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<?> saveAll(@RequestBody Iterable<Task> tasks, Principal principal) {

		Iterable<Task> saved = taskservice.saveAll(tasks, principal.getName().toLowerCase());

		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}


	@PostMapping("/admin/assignTask")
	public ResponseEntity<?> assignTask(@RequestBody Task task, @RequestParam String username) {

		Task saved = taskservice.save(task, username.toLowerCase());

		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delTask(@PathVariable Long id, Principal principal ) {

		boolean ok = taskservice.deleteTask(principal.getName().toLowerCase(),id);

		return new ResponseEntity<>(ok, HttpStatus.NO_CONTENT);
	}

}
