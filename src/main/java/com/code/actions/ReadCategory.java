package com.code.actions;

import com.code.entity.Category;
import org.hibernate.*;

public class ReadCategory {
	SessionFactory sessionFactory;
	public ReadCategory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Category readData(int id) {
		Session session = sessionFactory.openSession();
		Category category = session.get(Category.class, id);
		session.close();
		return category;
	}
}
