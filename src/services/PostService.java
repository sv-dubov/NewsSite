package services;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import entities.Post;

public class PostService {
	private Session session;

	public PostService(Session session) {
		super();
		this.session = session;
	}
	
	public Long Add(String title, String shortDescription, String description, String meta, String urlSlug,
			Boolean published, LocalDate postedOn, LocalDate modified, String category) {
		session.beginTransaction();
		Post post = new Post(title, shortDescription, description, meta, urlSlug,
				published, postedOn, modified, category);
		post.setTitle(title);
		post.setShortDescription(shortDescription);
		post.setDescription(description);
		post.setMeta(meta);
		post.setUrlSlug(urlSlug);
		post.setPublished(published);
		post.setPostedOn(postedOn);
		post.setModified(modified);
		post.setCategory(category);
		Long id = (Long)session.save(post);
		session.getTransaction().commit();
		return id;
	}
	public Post GetById(long id) {
		session.beginTransaction();
		Post post = (Post)session.get(Post.class, id);
		session.getTransaction().commit();
		return post;
	}
	public void Delete(long id) {
		Post post = this.GetById(id);
		session.beginTransaction();
		session.delete(post);
		session.getTransaction().commit();
	}
	public void Update(long id, String title, String shortDescription, String description, String meta, String urlSlug,
			Boolean published, LocalDate postedOn, LocalDate modified, String category) {
		Post post = this.GetById(id);
		session.beginTransaction();
		post.setTitle(title);
		post.setShortDescription(shortDescription);
		post.setDescription(description);
		post.setMeta(meta);
		post.setUrlSlug(urlSlug);
		post.setPublished(published);
		post.setPostedOn(postedOn);
		post.setModified(modified);
		post.setCategory(category);
		session.update(post);
		session.getTransaction().commit();
	}
	
	public List<Post> GetAll() {
		session.beginTransaction();
		List<Post> posts = session.createQuery("FROM Post").list();
		session.getTransaction().commit();
		return posts;
	}
}
