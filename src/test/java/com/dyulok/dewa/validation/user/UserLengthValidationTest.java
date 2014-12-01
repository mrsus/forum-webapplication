package com.dyulok.dewa.validation.user;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Test;

import com.dyulok.dewa.model.User;

public class UserLengthValidationTest {

	@Test
	public void testUserFirstNameLength(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	User user=new User();
	user.setFirstName("asdfasdfasdfasdsasasasas");
	user.setEmailId("emai");
	user.setLastName("as");
	user.setPassword("aa123a");

	Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
	
	List<ConstraintViolation<User>> list=new ArrayList<ConstraintViolation<User>>(constraintViolations);

	assertEquals("Your First name must between 1 and 20 characters", list.get(0).getMessage());
	}
	
	@Test
	public void testUserPasswordLength(){
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		User user=new User();
		user.setPassword("aaaa");
		user.setFirstName("abc");
		user.setEmailId("email");
		user.setLastName("as");
		

		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		
		List<ConstraintViolation<User>> list=new ArrayList<ConstraintViolation<User>>(constraintViolations);

		assertEquals("Your password must between 6 and 20 characters", list.get(0).getMessage());
	}

}
