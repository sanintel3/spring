package com.cts.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "TEST_USER", schema = "dbo", catalog = "master", uniqueConstraints = @UniqueConstraint(columnNames = "EMAIL_ID"))
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "FIRST_NAME", length = 100)
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 100)
	private String lastName;
	
	@Column(name = "EMAIL_ID", unique = true, nullable = false, length = 100)
	private String emailId;
	
	@Column(name = "EMPLOYEE_ID", unique = true, nullable = true, length = 50)
	private String employeeId;
	
	@Column(name = "ROLE", unique = true, nullable = true, length = 50)
	private String role;
	
	@Column(name = "PW", unique = true, nullable = true, length = 50)
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
