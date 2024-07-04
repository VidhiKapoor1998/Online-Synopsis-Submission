package com.university.student.javaBean;

import java.io.InputStream;
import java.util.Date;

public class Student {
	
	private String name;
	private String mail;
	private int rollNumber;
	private String course;
	private String password;
	private Date date;
	private InputStream synopsis;
	
	
	
	
	
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
	public int getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
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
		return "Student [name=" + name + ", mail=" + mail + "]";
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public InputStream getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(InputStream synopsis) {
		this.synopsis = synopsis;
	}
	
	
	
	
}

