package com.dyulok.dewa.dao.user;

import com.dyulok.dewa.customexceptions.LoginFailureException;
import com.dyulok.dewa.model.User;

public interface UserDao 
{
	public int addUser ( User user ); 
	public void deleteUser(User user);
	public int updateUser(User user);
	public User getUser(int id);  
	public User loginUser(String emailId,String password) throws LoginFailureException;

}
