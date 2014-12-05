package com.dyulok.dewa.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dyulok.dewa.model.Post;
import com.dyulok.dewa.service.post.PostServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/com/dyulok/dewa/test-bean.xml"})
public class PostServiceTest {

	@Autowired
	private PostServiceImpl postService;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@After
	public void cleanDatabase() {
		jdbcTemplate.update("delete from Post");
	}
	@Test
	public void testAddPost() {
		
		Post post=new Post();
		post.setPostTitle("Title");
		post.setPostCreator("abc");
		post.setUserID(10);
		int result=postService.addPost(post);
		
		assertNotSame(0, result);
		
		Post loadedPost= hibernateTemplate.get(Post.class, result);
		
		assertEquals(loadedPost.getPostTitle(),post.getPostTitle());
		assertEquals(loadedPost.getPostCreator(),post.getPostCreator());
		assertEquals(loadedPost.getUserID(),post.getUserID());
	}
	
	@Test
	public void testDeletePost(){
		Post post=new Post();
		post.setPostTitle("Title");
		post.setPostCreator("abc");
		post.setUserID(10);
		
		hibernateTemplate.save(post);
		int postId=post.getPost_ID();
		assertNotSame(0,postId);
		
		postService.deletePost(postId);
		int resultId=post.getPost_ID();
				
		Post loadedPost=hibernateTemplate.get(Post.class, resultId);
		assertNotSame(post,loadedPost);
		
	}
	
	@Test
	public void testUpdatePost(){
		Post post=new Post();
		post.setPostTitle("Title");
		post.setPostCreator("abc");
		post.setUserID(10);
		
		hibernateTemplate.save(post);
		int result=post.getPost_ID();
		assertNotSame(0, result);
		
		Post loadedPost=hibernateTemplate.get(Post.class, result);
		post.setPostTitle("something");
		post.setPostCreator("hello");
		post.setUserID(20);
		
		int expectedResult=postService.updatePost(loadedPost);
		
		Post loadPost=hibernateTemplate.get(Post.class, expectedResult);
		assertEquals(loadPost.getPost_ID(),post.getPost_ID());
		
		
	}
	
	@Test
	public void testGetPost(){
		Post post=new Post();
		post.setPostTitle("Title");
		post.setPostCreator("abc");
		post.setUserID(10);
		
		hibernateTemplate.save(post);
		int result=post.getPost_ID();
		assertNotSame(0, result);
		
		Post loadedPost=postService.getPost(result);
		assertEquals(loadedPost.getPostTitle(),post.getPostTitle());
		assertEquals(loadedPost.getPostCreator(),post.getPostCreator());
		assertEquals(loadedPost.getUserID(),post.getUserID());
	}
	
	@Test
	public void testLoadAllPostForUser(){
		Post post=new Post();
		post.setPostTitle("Title");
		post.setPostCreator("abc");
		post.setUserID(10);
		
		hibernateTemplate.save(post);
		int result=post.getPost_ID();
		assertNotSame(0, result);
		
		Post post1=new Post();
		post1.setPostTitle("xyz");
		post1.setPostCreator("hmm");
		post1.setUserID(10);
		hibernateTemplate.save(post1);
		int result1=post1.getPost_ID();
		assertNotSame(0, result1);
		
				
		List<Post> allComment=postService.loadAllPostForUser(post.getUserID());
		
		assertEquals(2, allComment.size());
		assertEquals(result, allComment.get(0).getPost_ID());
		assertEquals(post.getPostTitle(), allComment.get(0).getPostTitle());
		assertEquals(result1, allComment.get(1).getPost_ID());
		
	}

	@Test
	public void testLoadAllPost(){
		Post post=new Post();
		post.setPostTitle("Title");
		post.setPostCreator("abc");
		post.setUserID(10);
		
		hibernateTemplate.save(post);
		int result=post.getPost_ID();
		assertNotSame(0, result);
		
		Post post1=new Post();
		post1.setPostTitle("xyz");
		post1.setPostCreator("hmm");
		post1.setUserID(20);
		hibernateTemplate.save(post1);
		int result1=post1.getPost_ID();
		assertNotSame(0, result1);
		
		Post post2=new Post();
		post2.setPostTitle("asd");
		post2.setPostCreator("jkl");
		post2.setUserID(30);
		hibernateTemplate.save(post1);
		int result2=post1.getPost_ID();
		assertNotSame(0, result2);
		
		List<Post> allComment=postService.loadAllPost();
		
		assertEquals(3, allComment.size());
		assertEquals(result, allComment.get(0).getPost_ID());
		assertEquals(post.getPostTitle(), allComment.get(0).getPostTitle());
		assertEquals(result1, allComment.get(1).getPost_ID());
		
	}

}
