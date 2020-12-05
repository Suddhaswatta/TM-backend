package com.task.Task.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.Task.entity.User;
import com.task.Task.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public User saveUser(User user) {

		User saved = repo.save(user);

		return saved;

	}

	public User getByUsername(String username) {

		User user = repo.getByUsername(username);

		return user;

	}

}
