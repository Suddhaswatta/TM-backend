package com.task.Task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.Task.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User getByUsername(String username);
}
