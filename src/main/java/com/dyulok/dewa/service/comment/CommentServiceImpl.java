package com.dyulok.dewa.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dyulok.dewa.dao.comment.CommentDao;
import com.dyulok.dewa.model.Comment;

@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	
	@Override
	public int addComment(Comment comment) {
		
		return commentDao.addComment(comment);
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public void deleteComment(Comment comment) {
		commentDao.deleteComment(comment);
	}

	@Override
	public Comment getComment(int id) {
		return commentDao.getComment(id);
	}

	@Override
	public int updateComment(Comment comment) {
		return commentDao.updateComment(comment);
	}

	@Override
	public List<Comment> loadAllComment(int postId) {
		return commentDao.loadAllComment(postId);
	}

}
