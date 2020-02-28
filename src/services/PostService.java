package services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dto.CategoryDTO;
import dto.PostDTO;
import entities.Category;
import entities.Post;

public class PostService {
	private Session session;

	public PostService(SessionFactory sessionFactory) {
		this.session = sessionFactory.openSession();
	}
	
	public Post Add(PostDTO postDTO) {
		session.beginTransaction();
		Post post = new Post();
		post.setTitle(postDTO.getTitle());
		post.setShortDescription(postDTO.getShortDescription());
		post.setDescription(postDTO.getDescription());
		post.setMeta(postDTO.getMeta());
		post.setUrlSlug(postDTO.getUrlSlug());
		post.setPostedOn(postDTO.getPostedOn());
		//Long id = (Long)session.save(cat);
		session.save(post);
		session.getTransaction().commit();
		return post;
	}
	
	public void Test()
	{
		Query query = session.createQuery(
		        "select count(*) from Post");
		Long count = (Long)query.uniqueResult();
		System.out.println("---count Post---" + count);
	}
}
