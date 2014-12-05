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
@Table(name = "comment")
public class Comment 
{
	@Id
	@GeneratedValue
	@Column(name="COMMENT_ID")
	private int commentId;
	
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	@Column(name="POST_ID")
	private int postId;
	
	@Column(name="COMMENT")
	@NotEmpty(message="Comment must not be empty or null")
	@Length(min=1,max=100,message="Your comment must between 1 and 500 characters")
	private String comment;
	
	@Column(name="COMMENT_CREATOR")
	@NotEmpty(message="Comment Creator must not be empty or null")
	@Length(min=1,max=20,message="Comment creator must between 1 and 20 characters")
	private String commentCreator;
	
	public String getCommentCreator() {
		return commentCreator;
	}

	public void setCommentCreator(String commentCreator) {
		this.commentCreator = commentCreator;
	}

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public int getCommentId() {
		return commentId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
