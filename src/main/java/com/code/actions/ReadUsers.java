package com.code.actions;

import com.code.entity.Users;
import org.hibernate.*;

public class ReadUsers {
	SessionFactory sessionFactory;
	public ReadUsers(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Users readData(int id) {
		Session session = sessionFactory.getCurrentSession();
		Users user = session.get(Users.class, id);
		session.close();
		return user;
	}
}
