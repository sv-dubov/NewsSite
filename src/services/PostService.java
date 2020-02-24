package services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PostService {
	private Session session;

	public PostService(SessionFactory sessionFactory) {
		this.session = sessionFactory.openSession();
	}
	
	public void Test()
	{
		Query query = session.createQuery(
		        "select count(*) from Post");
		Long count = (Long)query.uniqueResult();
		System.out.println("---count Post---" + count);
	}
}
