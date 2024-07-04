package com.university.faculty.javaBean;

public class Faculty {
	private String name;
	private String mail;
	private int Id;
	private String course;
	private int pendingTask;
	private int completedtask;
	private String password;
	
	
	public int getPendingTask() {
		return pendingTask;
	}
	public void setPendingTask(int pendingTask) {
		this.pendingTask = pendingTask;
	}
	public int getCompletedtask() {
		return completedtask;
	}
	public void setCompletedtask(int completedtask) {
		this.completedtask = completedtask;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Faculty [Id=" + Id + "]";
	}
	
	
	
}
