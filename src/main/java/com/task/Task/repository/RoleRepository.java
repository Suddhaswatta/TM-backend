package com.task.Task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.Task.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role getByRoleName(String rolename);
}
