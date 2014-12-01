package com.dyulok.dewa.validation.post;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.dyulok.dewa.model.Post;

public class PostNullValidationTest {

	@Test
	public void testPostTitleNotNull(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	Post post=new Post();
	post.setPostTitle(null);
	post.setUserID(10);
	post.setPostCreator("abcasas");

	Set<ConstraintViolation<Post>> constraintViolations = validator.validate(post);
	
	List<ConstraintViolation<Post>> list=new ArrayList<ConstraintViolation<Post>>(constraintViolations);

	assertEquals("Post Title must not be empty or null", list.get(0).getMessage());
	}
	
	
	
	@Test
	public void testPostCreatorNotNull(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	Post post=new Post();
	post.setPostTitle("aaaasasasasasasasss");
	post.setUserID(10);
	post.setPostCreator(null);

	Set<ConstraintViolation<Post>> constraintViolations = validator.validate(post);
	
	List<ConstraintViolation<Post>> list=new ArrayList<ConstraintViolation<Post>>(constraintViolations);

	assertEquals("Post Creator must not be empty or null", list.get(0).getMessage());
	}
	
	

}
