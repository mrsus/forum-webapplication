package com.dyulok.dewa.dao.comment;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dyulok.dewa.model.Comment;


public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {

	
	@Override
	public int addComment(Comment comment) {
		getHibernateTemplate().save(comment);
		return comment.getCommentId();
	}

	@Override
	public void deleteComment(int commentId) {
		Comment comment=new Comment();
		comment.setCommentId(commentId);
		getHibernateTemplate().delete(comment);
	}
	@Override
	public Comment getComment(int id) {
		
		return getHibernateTemplate().load(Comment.class, id);
	}

	@Override
	public int updateComment(Comment comment) {
		getHibernateTemplate().update(comment);
		return comment.getCommentId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> loadAllComment(int postId) {
		
		 return (List<Comment>)getHibernateTemplate().find("from "+Comment.class.getName()+" c where c.postId = ?", postId);
		
	}

}
