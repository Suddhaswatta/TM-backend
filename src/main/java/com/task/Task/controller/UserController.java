package com.task.Task.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.Task.entity.Role;
import com.task.Task.entity.User;
import com.task.Task.repository.RoleRepository;
import com.task.Task.service.UserService;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepo;

	@GetMapping("/login")
	public ResponseEntity<?> login(Principal principal) {
		User user = service.getByUsername(principal.getName().toLowerCase());
		if (user == null)
			throw new UsernameNotFoundException("Username is not present in the directory");
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	
	@PostMapping("/registerAdmin")
	public ResponseEntity<?> registerAdmin(@RequestBody User user) {

		user.setUsername(user.getUsername().toLowerCase());

		User userRetrieved = service.getByUsername(user.getUsername());

		if (userRetrieved != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);

		Role roleAdmin = roleRepo.getByRoleName("ROLE_ADMIN");
		if(roleAdmin == null) {
			roleRepo.save(new Role(1L,"ROLE_ADMIN",null));
			roleAdmin = roleRepo.getByRoleName("ROLE_ADMIN");
		}
		Role roleUser = roleRepo.getByRoleName("ROLE_USER");
		if(roleUser == null) {
			roleRepo.save(new Role(2L,"ROLE_USER",null));
			roleUser = roleRepo.getByRoleName("ROLE_USER");
		}
		
		Set<Role> roles = new HashSet<>();

		roles.add(roleUser);
		roles.add(roleAdmin);

		user.setRoles(roles);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = service.saveUser(user);

		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

	}

	@PostMapping("/registerUser")
	public ResponseEntity<?> registerUser(@RequestBody User user) {

		user.setUsername(user.getUsername().toLowerCase());

		User userRetrieved = service.getByUsername(user.getUsername());

		if (userRetrieved != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);

		Role roleUser = roleRepo.getByRoleName("ROLE_USER");
		
		if(roleUser == null) {
			roleRepo.save(new Role(2L,"ROLE_USER",null));
			roleUser = roleRepo.getByRoleName("ROLE_USER");
		}
		

		Set<Role> roles = new HashSet<>();

		roles.add(roleUser);

		user.setRoles(roles);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		User savedUser = service.saveUser(user);

		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

	}

}
