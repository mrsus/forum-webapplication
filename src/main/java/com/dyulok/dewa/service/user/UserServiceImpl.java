package com.dyulok.dewa.service.user;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.transaction.annotation.Transactional;

import com.dyulok.dewa.dao.user.UserDao;
import com.dyulok.dewa.model.User;

@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public void deleteUser(int userId) {
		userDao.deleteUser(userId);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public User getUser(int id) {
		return userDao.getUser(id);
	}

}
