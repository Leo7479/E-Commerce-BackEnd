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
		session.beginTransaction();
		Users user = session.get(Users.class, id);
		session.getTransaction().commit();
		session.close();
		return user;
	}
}
