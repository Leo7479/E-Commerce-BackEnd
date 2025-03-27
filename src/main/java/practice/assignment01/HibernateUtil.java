package practice.assignment01;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.code.entity.*;

public class HibernateUtil {
	SessionFactory sessionFactory;
	public HibernateUtil(){
		this.sessionFactory = new Configuration().configure("hibernate.cfg.xml")
		.addAnnotatedClass(Category.class).addAnnotatedClass(Product.class)
		.addAnnotatedClass(Users.class).addAnnotatedClass(Orders.class)
		.addAnnotatedClass(OrderDetails.class)
		.buildSessionFactory();
	}
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
}
