package services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entities.Post;

public class HomeService {
	private Session session;

	public HomeService(SessionFactory sessionFactory) {
		this.session = sessionFactory.openSession();
	}
	
	public List<Post> GetAllHome() {
		session.beginTransaction();
		List<Post> posts = session.createQuery("FROM Post").list();
		session.getTransaction().commit();
		return posts;
	}
}
