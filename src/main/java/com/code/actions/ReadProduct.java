package com.code.actions;

import org.hibernate.*;
import com.code.entity.Product;

public class ReadProduct {
	SessionFactory sessionFactory;
	public ReadProduct(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Product readData(int id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = session.get(Product.class, id);
		session.close();
		return product;
	}
}
