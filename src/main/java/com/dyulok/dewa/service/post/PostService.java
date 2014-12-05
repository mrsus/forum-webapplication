package com.dyulok.dewa.service.post;

import java.util.List;

import com.dyulok.dewa.model.Post;

public interface PostService {

	public int addPost(Post post);
	public Post getPost(int id);
	public int updatePost(Post post);
	public void deletePost(int postId);
	public List<Post> loadAllPost();
	public List<Post> loadAllPostForUser(int userId);
}
