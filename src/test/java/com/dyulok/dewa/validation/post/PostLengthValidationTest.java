package com.dyulok.dewa.validation.post;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.dyulok.dewa.model.Post;

public class PostLengthValidationTest {

	@Test
	public void testPostTitleLength(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	Post post=new Post();
	post.setPostCreator("abcdasdsa");
	post.setPostTitle("asdbmdmasbdasdvasndasnnnnnnnnssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
			"ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
			"ssssssssssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaacvcccccccccccccccccccccccccccccccccccccccccccccasssssssssssssss" +
			"ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
			"sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
			"saaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
	post.setUserID(10);
	

	Set<ConstraintViolation<Post>> constraintViolations = validator.validate(post);
	
	List<ConstraintViolation<Post>> list=new ArrayList<ConstraintViolation<Post>>(constraintViolations);

	assertEquals("Your Post title must between 1 and 500 characters", list.get(0).getMessage());
	}
	@Test
	public void testPostCreatorLength(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	Post post=new Post();
	post.setPostTitle("aaaasasasasasasasss");
	post.setUserID(10);
	post.setPostCreator("asasaqqqqqqqqqqqqqqqqqq");

	Set<ConstraintViolation<Post>> constraintViolations = validator.validate(post);
	
	List<ConstraintViolation<Post>> list=new ArrayList<ConstraintViolation<Post>>(constraintViolations);

	assertEquals("Post creator must between 1 and 20 characters", list.get(0).getMessage());
	}

}
