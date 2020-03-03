package services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dto.CategoryDTO;
import entities.Category;
import entities.Post;

public class CategoryService {
	
	private Session session;

	private CategoryService(SessionFactory sessionFactory) {
		this.session = sessionFactory.openSession();
	}
	
	public Category Add(CategoryDTO catDTO) {
		session.beginTransaction();
		Category cat = new Category();
		cat.setName(catDTO.getName());
		cat.setDescription(catDTO.getDescription());
		cat.setUrlSlug(catDTO.getUrlSlug());
		session.save(cat);
		session.getTransaction().commit();
		return cat;
	}
	
	public Category GetById(long id) {
		session.beginTransaction();
		Category cat = (Category)session.get(Category.class, id);
		session.getTransaction().commit();
		return cat;
	}
	
	public void Delete(long id) {
		Category cat =  this.GetById(id);
		session.beginTransaction();
		session.delete(cat);
		session.getTransaction().commit();
	}
	
	public void Update(CategoryDTO catDTO) {
		Category cat = this.GetById(catDTO.getId());
		session.beginTransaction();
		cat.setName(catDTO.getName());
		cat.setUrlSlug(catDTO.getUrlSlug());
		cat.setDescription(catDTO.getDescription());
		session.update(cat);
		session.getTransaction().commit();
	}
	
	public List<Category> GetAll() {
		session.beginTransaction();
		List<Category> categories = session.createQuery("FROM Category").list();
		session.getTransaction().commit();
		return categories;
	}
}
