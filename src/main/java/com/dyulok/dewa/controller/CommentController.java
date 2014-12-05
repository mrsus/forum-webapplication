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
import com.dyulok.dewa.model.Comment;
import com.dyulok.dewa.service.comment.CommentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@RequestMapping(value = "/comment")
@Controller
public class CommentController {
	
	
	private CommentService commentService;
	public CommentController() {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/com/dyulok/dewa/main-bean.xml");
		 commentService=(CommentService)ctx.getBean("commentService");
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody JSONObject addComment(@RequestParam("json_data")String jsonData) {
		int id;
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		
		Map<String, String> mapData = gson.fromJson(jsonData, type);
		JsonInputValidator jsonInputValidator = new JsonInputValidator();
		ValidationErrorMessage errorMessage = jsonInputValidator.validate(Comment.class, mapData);
		
		if (errorMessage != null) {
			JSONObject jsonString = generateJsonString(errorMessage.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for adding comment");
			errLogger.error("Getting error for adding comment because:{}"+errorMessage.getMessage());
			return jsonString;
		}
		
		Comment comment = gson.fromJson(jsonData, Comment.class);
		try {
			id = commentService.addComment(comment);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("Successfully added comment for commentId:"+id);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for adding comment");
			errLogger.error("Getting error for adding comment because:{}"+e.getMessage());
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(id, "success");
		return jsonString;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public @ResponseBody JSONObject deleteComment(@RequestParam("commentId") int commentId) {
		try {
			commentService.deleteComment(commentId);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("Successfully deleted comment for commentId:"+commentId);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for deleting comment for commentId:{}"+commentId);
			errLogger.error("Getting error for deleting comment because:{}"+e.getMessage());
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(commentId,
				"success");
		return jsonString;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody JSONObject updateComment(@RequestParam("json_data")String jsonData) {
		int id;
		Gson gson = new GsonBuilder().create();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		
		Map<String, String> mapData = gson.fromJson(jsonData, type);
		JsonInputValidator jsonInputValidator = new JsonInputValidator();
		ValidationErrorMessage errorMessage = jsonInputValidator.validate(Comment.class, mapData);
		
		if (errorMessage != null) {
			JSONObject jsonString = generateJsonString(errorMessage.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for updating comment");
			errLogger.error("Getting error for updating comment because:{}"+errorMessage.getMessage());
			return jsonString;
		}
		
		Comment comment = gson.fromJson(jsonData, Comment.class);
		try {
			id = commentService.updateComment(comment);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("Successfully updated comment for commentId:"+id);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Getting error for updating comment");
			errLogger.error("Getting error for updating comment because:{}"+e.getMessage());
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(id, "success");
		return jsonString;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody JSONObject getComment(@RequestParam("commentId") int commentId) {
		Comment comment = null;
		try {
			comment = commentService.getComment(commentId);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("getting comment for commentId:"+commentId);
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject jsonString = generateJsonString(e.getMessage(),"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("error for getting comment for commentId:"+commentId);
			errLogger.error("error for getting comment because:{}",e);
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(comment, "success");
		return jsonString;
	}

	@RequestMapping(value = "/loadallcomment", method = RequestMethod.GET)
	public @ResponseBody JSONObject getAllComment(@RequestParam("postId") int postId) {
		List<Comment> allComment;
		try {
			allComment = commentService.loadAllComment(postId);
			Logger infoLogger=LoggerFactory.getLogger("infoLogger");
			infoLogger.info("getting all comment for postId:"+postId);
		} catch (Exception e) {
			JSONObject jsonString = generateJsonString(e.getMessage(),
					"failure");
			Logger errLogger = LoggerFactory.getLogger("debugLogger");
			errLogger.error("Failed for getting all comment for postId:"+postId);
			errLogger.error("Failed for getting all comment because:{}",e);
			return jsonString;
		}
		JSONObject jsonString = generateJsonString(allComment, "success");
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
