package com.dyulok.dewa.service.comment;

import java.util.List;

import com.dyulok.dewa.model.Comment;

public interface CommentService {

	public int addComment(Comment comment);
	public void deleteComment(int commentId);
	public int updateComment(Comment comment);
	public List<Comment> loadAllComment(int postId);
	public Comment getComment(int id);
}
