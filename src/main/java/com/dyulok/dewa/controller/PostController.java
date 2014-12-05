package com.dyulok.dewa.controller;

import java.lang.reflect.Type;
import java.util.List;
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
import com.dyulok.dewa.model.Post;
import com.dyulok.dewa.service.post.PostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping(value = "/post")
public class PostController {

	
	private PostService postService;
	public PostController() {
		 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/com/dyulok/dewa/main-bean.xml");
		 postService=(PostService)ctx.getBean("postService");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPost(@RequestParam("json_data")String jsonData) {
		int id;
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		
		Map<String, String> mapData = gson.fromJson(jsonData, type);
		JsonInputValidator jsonInputValidator = new JsonInputValidator();
		ValidationErrorMessage errorMessage = jsonInputValidator.validate(Post.class, mapData);
		
		if (errorMessage != null) {
			JSONObject jsonString = generateJsonString(errorMessage.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for adding post");
			errLogger.error("Getting error for adding post because:{}"+errorMessage.getMessage());
			return jsonString;
		}
		Post post = gson.fromJson(jsonData, Post.class);
		try {
			id = postService.addPost(post);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("Successfully added post for postId:"+id);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for adding post");
			errLogger.error("Getting error for adding post because:{}"+e.getMessage());
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(id, "success");
		return jsonString;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody JSONObject deletePost(@RequestParam("postId") int postId) {
		try {
			postService.deletePost(postId);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("Successfully deleted user for userId:"+postId);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for deleting post for postId:{}"+postId);
			errLogger.error("Getting error for deleting post because:{}"+e.getMessage());
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(postId, "success");
		return jsonString;

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody JSONObject updatePost(@RequestParam("json_data")String jsonData) {
		int id;
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		
		Map<String, String> mapData = gson.fromJson(jsonData, type);
		JsonInputValidator jsonInputValidator = new JsonInputValidator();
		ValidationErrorMessage errorMessage = jsonInputValidator.validate(Post.class, mapData);
		
		if (errorMessage != null) {
			JSONObject jsonString = generateJsonString(errorMessage.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for updating post");
			errLogger.error("Getting error for updating post because:{}"+errorMessage.getMessage());
			return jsonString;
		}
		Post post = gson.fromJson(jsonData, Post.class);
		try {
			id = postService.updatePost(post);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("Successfully updated post for postId:"+id);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for updating post");
			errLogger.error("Getting error for updating post because:{}"+e.getMessage());
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(id, "success");
		return jsonString;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody JSONObject getPost(@RequestParam("postId") int postId) {
		Post post;
		try {			
			post = postService.getPost(postId);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("getting post for postId:"+postId);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for getting post for postId:"+postId);
			errLogger.error("Getting error for getting post because:{}",e);
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(post, "success");
		return jsonString;
	}

	@RequestMapping(value = "/allpost", method = RequestMethod.GET)
	public @ResponseBody JSONObject getAllPost() {
		List<Post> allPost;
		try {
			allPost = postService.loadAllPost();
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("getting all post");
			
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Failed for getting all post");
			errLogger.error("Failed for getting all post because:{}",e);
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(allPost, "success");
		return jsonString;

	}

	@RequestMapping(value = "/getuserpost", method = RequestMethod.GET)
	public @ResponseBody JSONObject getAllPostForUser(@RequestParam("userId") int userId) {
		List<Post> allPost;
		try {
			allPost = postService.loadAllPostForUser(userId);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("getting all user posts for userId:"+userId);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Failed for getting all posts for perticular user with userId:"+userId);
			errLogger.error("Failed for getting all posts for perticular user because: {}",e);
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(allPost, "success");
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
