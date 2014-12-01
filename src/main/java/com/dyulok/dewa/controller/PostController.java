package com.dyulok.dewa.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyulok.dewa.model.Post;
import com.dyulok.dewa.service.post.PostService;

@Controller
@RequestMapping(value = "/post")
public class PostController {

	
	private PostService postService;
	public PostController() {
		 ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/com/dyulok/dewa/main-bean.xml");
		 postService=(PostService)ctx.getBean("postService");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JSONObject addPost(Post post) {
		int id;
		try {
			id = postService.addPost(post);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(id, "success");
		return jsonString;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody JSONObject deletePost(Post post) {
		try {
			postService.deletePost(post);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(post.getPost_ID(), "success");
		return jsonString;

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody JSONObject updatePost(Post post) {
		int id;
		try {
			id = postService.updatePost(post);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
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
			errLogger.error("error for getting post for postId:"+postId);
			errLogger.error("error for getting post because:{}",e);
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
