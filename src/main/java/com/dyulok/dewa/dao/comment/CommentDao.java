package com.dyulok.dewa.dao.comment;

import java.util.List;

import com.dyulok.dewa.model.Comment;

public interface CommentDao 
{
	public int addComment(Comment comment);
	public void deleteComment(Comment comment);
	public Comment getComment(int id);
	public List<Comment> loadAllComment(int postId);
	public int updateComment(Comment comment);
}
