package com.dyulok.dewa.validation.user;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.dyulok.dewa.model.User;


public class UserNullValidationTest {

	@Test
	public void testUserFirstNameNotNull(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	User user=new User();
	user.setFirstName(null);
	user.setEmailId("emai");
	user.setLastName("as");
	user.setPassword("aa123a");

	Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
	
	List<ConstraintViolation<User>> list=new ArrayList<ConstraintViolation<User>>(constraintViolations);

	assertEquals("First Name must not be empty or null", list.get(0).getMessage());
	}
	
	
	
	@Test
	public void testUserEmailIdNotNull(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		User user=new User();
		user.setFirstName("abc");
		user.setEmailId(null);
		
		user.setLastName("as");
		user.setPassword("aaa123");

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		
		List<ConstraintViolation<User>> list=new ArrayList<ConstraintViolation<User>>(constraintViolations);

		assertEquals("Email must not be empty or null", list.get(0).getMessage());
	}
	@Test
	public void testUserPasswordNotNull(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		User user=new User();
		user.setFirstName("asd");
		user.setEmailId("email");
		user.setLastName("as");
		user.setPassword(null);
		
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		
		List<ConstraintViolation<User>> list=new ArrayList<ConstraintViolation<User>>(constraintViolations);

		assertEquals("Password must not be empty or null", list.get(0).getMessage());
	}
	
}
	
