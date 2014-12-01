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
@Table(name = "post")
public class Post 
{
	@Id
	@GeneratedValue
	@Column(name="POST_ID")
	private int post_ID;
	
	@Column(name="POST_TITLE")
	@NotEmpty(message="Post Title must not be empty or null")
	@Length(min=1,max=500,message="Your Post title must between 1 and 500 characters")
	private String postTitle;
	
	@Column(name="USER_ID")
	private int userID;
	
	@Column(name="POST_CREATOR")
	@NotEmpty(message="Post Creator must not be empty or null")
	@Length(min=1,max=20,message="Post creator must between 1 and 20 characters")
	private String postCreator;
	
	public String getPostCreator() {
		return postCreator;
	}

	public void setPostCreator(String postCreator) {
		this.postCreator = postCreator;
	}

	public Post() {
		// TODO Auto-generated constructor stub
	}

	public int getPost_ID() {
		return post_ID;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	

}
