package com.dyulok.dewa.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity  
@Proxy(lazy=false)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "user") 
public class User 
{
	 @Id  
	 @GeneratedValue  
	 @Column(name = "USER_ID") 
	 private int userID;
	
	 @Column(name = "FIRST_NAME")
	 @NotEmpty(message="First Name must not be empty or null")
	 @Length(min=1,max=20,message="Your First name must between 1 and 20 characters")
	 private String firstName;
	
	 @Column(name="LAST_NAME")
	 private String lastName;
	
	 @Column(name="PASSWORD")
	 @NotEmpty(message="Password must not be empty or null")
	 @Length(min=6,max=20,message="Your password must between 6 and 20 characters")
	 private String password;
	
	 @Column(name="EMAIL_ID")
	 @NotEmpty(message="Email must not be empty or null")
	 private String emailId;
	
	 public User() {
		// TODO Auto-generated constructor stub
	}

	 
	public int getUserID() {
		return userID;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	

}
