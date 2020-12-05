package com.task.Task.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.Task.entity.User;
import com.task.Task.repository.UserRepository;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

	@Autowired
	private UserRepository userRepo;
	

	@GetMapping("/userOptions")
	public ResponseEntity<?> getAllUsersOptions() {

		Iterable<User> users = userRepo.findAll();

		List<Map<String, String>> userDetails = new ArrayList<>();

		for (User user : users) {
			Map<String, String> map = new HashMap<>();
			map.put("username", user.getUsername());
			map.put("firstname", user.getFirstname());
			map.put("lastname", user.getLastname());

			userDetails.add(map);

		}
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}
	

}
