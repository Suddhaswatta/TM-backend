package com.task.Task.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Task implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5579607204102105337L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@CreatedDate
	private Date start;
	private Date deadline;
	
	private String description;
	private String title;
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
	private User user;

	public Task() {
		super();
	}

	public Task(Long id, Date deadline, String description, String title, String status, User user) {
		super();
		this.id = id;
		this.start = new Date();
		this.deadline = deadline;
		this.description = description;
		this.title = title;
		this.status = status;
		this.user = user;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", start=" + start + ", deadline=" + deadline + ", description=" + description
				+ ", title=" + title + ", status=" + status + ", user=" + user + "]";
	}
	
	
	
	
	

}
