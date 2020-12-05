package com.task.Task.service;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.Task.entity.Role;
import com.task.Task.entity.User;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username)  {
		User user = service.getByUsername(username);
		
		if (user == null) {
		      throw new UsernameNotFoundException("No user found for "+ username + ".");
		    }

		Set<GrantedAuthority> authorities = new HashSet<>();

		for (Role role : user.getRoles()) {

			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

}
