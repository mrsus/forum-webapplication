package com.dyulok.dewa.dao.post;

import java.util.List;

import com.dyulok.dewa.model.Post;

public interface PostDao 
{
	public int addPost(Post post);
	public Post getPost(int id);
	public List<Post> loadAllPost();
	public List<Post> loadAllPostForUser(int userId);
	public int updatePost(Post post);
	public void deletePost(int postId);
}
