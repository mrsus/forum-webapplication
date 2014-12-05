package com.dyulok.dewa.controller;

import java.lang.reflect.Type;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyulok.dewa.controller.validation.JsonInputValidator;
import com.dyulok.dewa.controller.validation.ValidationErrorMessage;
import com.dyulok.dewa.model.User;
import com.dyulok.dewa.service.user.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RequestMapping(value = "/user")
@Controller
public class UserController {

	
	private UserService userService;

	public UserController() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/com/dyulok/dewa/main-bean.xml");
		 userService=(UserService)ctx.getBean("userService");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JSONObject addUser(@RequestParam("json_data")String jsonData) {
		int id;
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		
		Map<String, String> mapData = gson.fromJson(jsonData, type);
		JsonInputValidator jsonInputValidator = new JsonInputValidator();
		ValidationErrorMessage errorMessage = jsonInputValidator.validate(User.class, mapData);
		
		if (errorMessage != null) {
			JSONObject jsonString = generateJsonString(errorMessage.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for adding user");
			errLogger.error("Getting error for adding user because:{}"+errorMessage.getMessage());
			return jsonString;
		}
		
		User user = gson.fromJson(jsonData, User.class);
		try {
			id = userService.addUser(user);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("Successfully added user for userId:"+id);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for adding user");
			errLogger.error("Getting error for adding user because:{}"+e.getMessage());
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(id, "success");
		return jsonString;
	}

	

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody JSONObject updateUser(@RequestParam("json_data")String jsonData) {
		int id;
		Gson gson = new GsonBuilder().create();
		Type type=new TypeToken<Map<String,String>>(){}.getType();
		Map<String, String> mapData = gson.fromJson(jsonData, type);
		JsonInputValidator jsonInputValidator = new JsonInputValidator();
		ValidationErrorMessage errorMessage = jsonInputValidator.validate(User.class, mapData);
		
		if (errorMessage != null) {
			JSONObject jsonString = generateJsonString(errorMessage.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for updating user");
			errLogger.error("Getting error for updating user because:{}"+errorMessage.getMessage());
			return jsonString;
		}		
		
		User user = gson.fromJson(jsonData, User.class);
		try {
			id = userService.updateUser(user);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("Successfully updated user details for userId:"+id);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for updating user");
			errLogger.error("Getting error for updating user details because:{}"+e.getMessage());
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(id, "success");
		return jsonString;
	}

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public @ResponseBody JSONObject deleteUser(@RequestParam("userId") int userId) {

		try {
			userService.deleteUser(userId);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("Successfully deleted user for userId:"+userId);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for deleting user for userId:{}"+userId);
			errLogger.error("Getting error for deleting user because:{}"+e.getMessage());
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(userId, "success");
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
			errLogger.error("error for getting user for userId:"+userId);
			errLogger.error("error for getting user for userId because :{}",e);
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
