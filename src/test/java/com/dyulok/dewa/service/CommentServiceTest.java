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

import com.dyulok.dewa.model.Comment;
import com.dyulok.dewa.service.comment.CommentServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/com/dyulok/dewa/test-bean.xml"})
public class CommentServiceTest {

	@Autowired
	private CommentServiceImpl commentService;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@After
	public void cleanDatabase(){
		jdbcTemplate.update("delete from Comment");
	}
	
	@Test
	public void testAddComment(){
		Comment comment=new Comment();
		comment.setComment("how r u");
		comment.setCommentCreator("ABC");
		comment.setPostId(10);
		
		int result=commentService.addComment(comment);
		assertNotSame(0, result);
		
		Comment loadedComment=(Comment) hibernateTemplate.get(Comment.class, result);
		assertEquals(loadedComment.getComment(), comment.getComment());
		assertEquals(loadedComment.getPostId(), comment.getPostId());
		assertEquals(loadedComment.getCommentCreator(), comment.getCommentCreator());
	}
	@Test
	public void testDeleteComment(){
		Comment comment=new Comment();
		comment.setComment("how r u");
		comment.setCommentCreator("ABC");
		comment.setPostId(15);
		hibernateTemplate.save(comment);
		int result=comment.getCommentId();
		assertNotSame(0, result);
		
		commentService.deleteComment(comment);
		int deletedId=comment.getCommentId();
		
		Comment loadedComment=hibernateTemplate.get(Comment.class, deletedId);
		assertNotSame(loadedComment, comment);
		
	}
	@Test
	public void testUpdateComment(){
		Comment comment=new Comment();
		comment.setComment("how r u");
		comment.setCommentCreator("ABC");
		comment.setPostId(10);
		hibernateTemplate.save(comment);
		int result=comment.getCommentId();
		assertNotSame(0, result);
		
		Comment loadedComment=hibernateTemplate.get(Comment.class, result);
		loadedComment.setComment("hows u");
		comment.setCommentCreator("Someone");
		loadedComment.setPostId(20);
		
		int expectedResult=commentService.updateComment(loadedComment);
		Comment loadComment=hibernateTemplate.get(Comment.class, expectedResult);
		
		assertEquals(loadComment.getCommentId(), comment.getCommentId());
		
	}
	
	@Test
	public void testGetComment(){
		Comment comment=new Comment();
		comment.setComment("how r u");
		comment.setCommentCreator("ABC");
		comment.setPostId(30);
		hibernateTemplate.save(comment);
		int result=comment.getCommentId();
		assertNotSame(0, result);
		
		Comment loadedComment=hibernateTemplate.get(Comment.class, result);
		assertEquals(loadedComment.getComment(), comment.getComment());
		assertEquals(loadedComment.getPostId(), comment.getPostId());
		assertEquals(loadedComment.getCommentCreator(), comment.getCommentCreator());
	}
	
	@Test
	public void testLoadAllComment(){
		Comment comment=new Comment();
		comment.setComment("how r u");
		comment.setCommentCreator("ABC");
		comment.setPostId(30);
		hibernateTemplate.save(comment);
		int result=comment.getCommentId();
		assertNotSame(0, result);
		
		Comment comment1=new Comment();
		comment1.setComment("how r u");
		comment1.setCommentCreator("ABC");
		comment1.setPostId(30);
		hibernateTemplate.save(comment1);
		int result1=comment1.getCommentId();
		assertNotSame(0, result1);
		
				
		List<Comment> allComment=commentService.loadAllComment(comment.getPostId());
		
		assertEquals(2, allComment.size());
		assertEquals(result, allComment.get(0).getCommentId());
		assertEquals(result1, allComment.get(1).getCommentId());
		
	}
}
