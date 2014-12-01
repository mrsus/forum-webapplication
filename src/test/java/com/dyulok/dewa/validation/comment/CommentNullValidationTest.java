package com.dyulok.dewa.validation.comment;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.dyulok.dewa.model.Comment;

public class CommentNullValidationTest {

	@Test
	public void testCommentNotNull(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Comment comment=new Comment();
		comment.setComment(null);
		comment.setCommentCreator("aa");
		comment.setPostId(10);
	
		Set<ConstraintViolation<Comment>> constraintViolations = validator.validate(comment);
	
		List<ConstraintViolation<Comment>> list=new ArrayList<ConstraintViolation<Comment>>(constraintViolations);
		assertEquals("Comment must not be empty or null", list.get(0).getMessage());
	}
	
	@Test
	public void testCommentCreatorNotNull(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Comment comment=new Comment();
		comment.setComment("abc");
		comment.setCommentCreator(null);
		comment.setPostId(10);
	
		Set<ConstraintViolation<Comment>> constraintViolations = validator.validate(comment);
	
		List<ConstraintViolation<Comment>> list=new ArrayList<ConstraintViolation<Comment>>(constraintViolations);
		assertEquals("Comment Creator must not be empty or null", list.get(0).getMessage());
		}

}
