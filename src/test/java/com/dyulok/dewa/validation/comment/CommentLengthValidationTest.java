package com.dyulok.dewa.validation.comment;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.dyulok.dewa.model.Comment;

public class CommentLengthValidationTest {

	@Test
	public void testCommentLength(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Comment comment=new Comment();
		comment.setComment("asdbmdmasbdasdvasndasnnnnnnnnssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
			"ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
			"ssssssssssssssssssssssssssssaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaacvcccccccccccccccccccccccccccccccccccccccccccccasssssssssssssss" +
			"ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
			"sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss" +
			"saaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");
		comment.setCommentCreator("aa");
		comment.setPostId(10);
	
		Set<ConstraintViolation<Comment>> constraintViolations = validator.validate(comment);
	
		List<ConstraintViolation<Comment>> list=new ArrayList<ConstraintViolation<Comment>>(constraintViolations);
		assertEquals("Your comment must between 1 and 500 characters", list.get(0).getMessage());
	}

	@Test
	public void testCommentCreatorLength(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Comment comment=new Comment();
		comment.setComment("abc");
		comment.setCommentCreator("asdasdwesdewsdedfcvfdeddf");
		comment.setPostId(10);
	
		Set<ConstraintViolation<Comment>> constraintViolations = validator.validate(comment);
	
		List<ConstraintViolation<Comment>> list=new ArrayList<ConstraintViolation<Comment>>(constraintViolations);
		assertEquals("Comment creator must between 1 and 20 characters", list.get(0).getMessage());
		}
}
