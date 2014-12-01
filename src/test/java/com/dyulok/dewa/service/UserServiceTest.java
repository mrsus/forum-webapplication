package com.dyulok.dewa.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dyulok.dewa.model.User;
import com.dyulok.dewa.service.user.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/com/dyulok/dewa/test-bean.xml"})
public class UserServiceTest {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@After
	public void cleanDatabase() {
		jdbcTemplate.update("delete from USER");
	}
	@Test
	public void testAddUser() {
		
		User user=new User();
		user.setEmailId("abc@as.as");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setPassword("password");
		
		int result=userService.addUser(user);
		
		assertNotSame(0, result);
		
		User loadedUser=hibernateTemplate.get(User.class, result);
		assertEquals(loadedUser.getEmailId(), user.getEmailId());
		assertEquals(loadedUser.getFirstName(),user.getFirstName());
		assertEquals(loadedUser.getLastName(), user.getLastName());
		assertEquals(loadedUser.getPassword(), user.getPassword());
	}
	
	@Test
	public void testDeleteUser(){
		User user=new User();
		user.setEmailId("xyz@as.as");
		user.setFirstName("firstName1");
		user.setLastName("lastName1");
		user.setPassword("password1");
		
		hibernateTemplate.save(user);
		int result=user.getUserID();
		assertNotSame(0,result);
		
		userService.deleteUser(user);
		int resultId=user.getUserID();
		
		User loadedUser=hibernateTemplate.get(User.class, resultId);
		assertNotSame(user,loadedUser);
		
	}
	
	@Test
	public void testUpdateUser(){
		User user=new User();
		user.setEmailId("asd@as.as");
		user.setFirstName("firstName2");
		user.setLastName("lastName3");
		user.setPassword("password4");
		
		hibernateTemplate.save(user);
		int result=user.getUserID();
		assertNotSame(0, result);
		
		User loadedUser=hibernateTemplate.get(User.class, result);
		loadedUser.setEmailId("email.com");
		loadedUser.setFirstName("someone");
		loadedUser.setLastName("something");
		loadedUser.setPassword("anything");
		
		int expectedResult=userService.updateUser(loadedUser);
		
		User loadUser=hibernateTemplate.get(User.class, expectedResult);
		assertEquals(loadUser.getUserID(),user.getUserID());
		
		
	}
	
	@Test
	public void testGetUser(){
		User user=new User();
		user.setEmailId("abc@as.as");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setPassword("password");
		hibernateTemplate.save(user);
		int result=user.getUserID();
		
		assertNotSame(0, result);
		User loadedUser=userService.getUser(result);
		assertEquals(loadedUser.getEmailId(), user.getEmailId());
		assertEquals(loadedUser.getFirstName(),user.getFirstName());
		assertEquals(loadedUser.getLastName(), user.getLastName());
		assertEquals(loadedUser.getPassword(), user.getPassword());
		
	}

}
