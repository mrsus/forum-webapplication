package com.dyulok.dewa.dao.post;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dyulok.dewa.model.Post;
public class PostDaoImpl extends HibernateDaoSupport implements PostDao {

	@Override
	public int addPost(Post post) {
		getHibernateTemplate().save(post);
		return post.getPost_ID();
	}

	@Override
	public Post getPost(int id) {
		return getHibernateTemplate().load(Post.class, id);
	}

	@Override
	public void deletePost(int postId) {
		Post post=new Post();
		post.setPost_ID(postId);
		getHibernateTemplate().delete(post);

	}

	@Override
	public int updatePost(Post post) {
		getHibernateTemplate().update(post);
		return post.getPost_ID();
	}

	

	@Override
	public List<Post> loadAllPost() {
		return getHibernateTemplate().loadAll(Post.class);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Post> loadAllPostForUser(int userId) {
		return (List<Post>)getHibernateTemplate().find("from "+Post.class.getName()+" p where p.userID = ?", userId);
		
	}

	

}
