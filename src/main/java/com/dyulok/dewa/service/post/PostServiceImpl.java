package com.dyulok.dewa.service.post;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;  

import com.dyulok.dewa.dao.post.PostDao;
import com.dyulok.dewa.model.Post;

@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	PostDao postDao;
	
	@Override
	public int addPost(Post post) {
		
		return postDao.addPost(post);
	}

	public PostDao getPostDao() {
		return postDao;
	}

	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
	public Post getPost(int id) {
		return postDao.getPost(id);
	}

	@Override
	public void deletePost(Post post) {
		postDao.deletePost(post);

	}

	@Override
	public int updatePost(Post post) {
		return postDao.updatePost(post);
	}

	@Override
	public List<Post> loadAllPost() {
		return postDao.loadAllPost();
	}

	@Override
	public List<Post> loadAllPostForUser(int userId) {
		return postDao.loadAllPostForUser(userId);
	}

}
