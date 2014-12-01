package com.dyulok.dewa.controller;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyulok.dewa.model.User;
import com.dyulok.dewa.service.user.UserService;

@RequestMapping(value = "/user")
@Controller
public class UserController {

	
	private UserService userService;

	public UserController() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/com/dyulok/dewa/main-bean.xml");
		 userService=(UserService)ctx.getBean("userService");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JSONObject addUser(User user) {
		int id;
		try {
			id = userService.addUser(user);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(id, "success");
		return jsonString;
	}

	@RequestMapping("/delete")
	public @ResponseBody JSONObject deleteUser(User user) {

		try {
			userService.deleteUser(user);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(user.getUserID(), "success");
		return jsonString;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody JSONObject updateUser(User user) {
		int id;
		try {
			id = userService.updateUser(user);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(id, "success");
		return jsonString;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody JSONObject getUser(@RequestParam("userId") int userId) {
		User user;
		try {
			user = userService.getUser(userId);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("getting user for userId:"+userId);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("error for getting post for postId:"+userId);
			errLogger.error("error for getting post for postId because :{}",e);
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(user, "success");
		return jsonString;
	}

	@SuppressWarnings("unchecked")
	private JSONObject generateJsonString(Object obj, String status) {
		JSONObject json = new JSONObject();
		json.put("status", status);
		json.put("data", obj);
		return json;
	}

}
