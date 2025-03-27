package com.code.actions;

import com.code.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

public class InsertProduct {
	SessionFactory sessionFactory;
	public InsertProduct(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void insertData(String name, float price, int stockQuantity, Category category) {
		if(name.length() > 20) {
			System.out.println("Length of NAME is out of permissible length.");
			return;
		}
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		// Try to fetch the category from the database first
		// Explanation below the code on why we want to fetch the category from the database again
	    Category existingCategory = session.get(Category.class, category.getId());
	    if (existingCategory != null) {
	        category = existingCategory; // Use the fetched category
	    } else {
	        // Persist the new category if it doesn't exist
	        session.persist(category);
	    }
		Product product = new Product(name, price, stockQuantity, category);
		session.persist(product);
		session.getTransaction().commit();
		session.close();
		System.out.println("New Product Inserted:\n"+product.toString());
	}
}


/* Findings: 
Hibernate maintains several entity states as they move through the persistence process. The main states are:
Transient: An entity is in this state if it has been created in memory but is not yet associated with a Hibernate session. It is not persisted in the database.
Persistent: An entity becomes persistent when it is associated with an active Hibernate session, meaning the session is aware of it. It’s either already saved in the database or has been loaded from it.
Detached: An entity becomes detached when it was once persistent (i.e., was part of a session and saved), but the session has been closed, and it’s no longer associated with the session.

When you use session.persist(category) before persisting the Product, Hibernate makes the Category entity persistent. It is now associated with the session, meaning it’s being tracked by Hibernate and can be safely referenced by any other entity, such as Product.
Once the Category is persisted, the Product can safely reference it, because now both the Product and Category are in the same session context and Hibernate can handle the relationship between them correctly.
Alternatively, if you fetch the Category from the database using session.get(Category.class, category.getId()), Hibernate ensures that the Category you're using is already a persistent entity (since it was loaded from the database within the current session).
The result is that the Category is in the same session context as the Product, so no detached entity issues occur.

*/