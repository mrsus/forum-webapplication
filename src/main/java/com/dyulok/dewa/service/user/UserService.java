package com.dyulok.dewa.service.user;

import com.dyulok.dewa.model.User;

public interface UserService 
{
	public int addUser ( User user ); 
	public void deleteUser(User user);
	public int updateUser(User user);
	public User getUser(int id);

}
