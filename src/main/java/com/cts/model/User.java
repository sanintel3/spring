package com.cts.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	
	@Length(max=5)
	private String firstName;
	private String lastName;
	@NotEmpty(message = "Email Id can't be empty")
	private String emailId;
	private String employeeId;
	private Integer id;
	private String role;
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
			
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
