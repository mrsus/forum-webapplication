package com.dyulok.dewa.dao.user;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dyulok.dewa.customexceptions.LoginFailureException;
import com.dyulok.dewa.model.User;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {  
	
	@Override
	public int addUser(User user) {
		getHibernateTemplate().save(user);
		
		return user.getUserID();
	}

	@Override
	public void deleteUser(int userId) {
		User user=new User();
		user.setUserID(userId);
		getHibernateTemplate().delete(user);
	}

	@Override
	public int updateUser(User user) {
		getHibernateTemplate().update(user);
		return user.getUserID();

	}

	@Override
	public User getUser(int id) {
		return getHibernateTemplate().load(User.class, id);
	}

	@Override
	public User loginUser(String emailId, String password)
			throws LoginFailureException {
		String hql="FROM "+User.class.getName()+" u WHERE u.emailID = :emailId"+ " And u.password= :password";
		Query query=getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("emailId", emailId);
		query.setParameter("password", password);
		@SuppressWarnings("unchecked")
		List<User> result=query.list();
		if(result==null || result.isEmpty())
			throw new LoginFailureException("Login Failed");
						
		return result.get(0);
	}

}
