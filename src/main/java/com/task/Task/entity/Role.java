package com.task.Task.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1653425238972834601L;

	@Id
	private Long id;
	
	private String roleName;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<User> users;

	
	public Role() {
		super();
	}


	public Role(Long id, String roleName, List<User> users) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.users = users;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", users=" + users + "]";
	}
	
	
	
	

}
