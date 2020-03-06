package services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.UserDTO;
import entities.Role;
import entities.User;
import validation.EmailExistsException;

//@Service("registrationService")
@Service
public class RegistrationService implements IRegistrationService {
	@Autowired
	private SessionFactory sessionFactory;
	// private HibernateTemplate hibernateTemplate;
	private static Logger log = Logger.getLogger(AuthService.class);

//	public SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@Override
	public User registerNewUserAccount(UserDTO accountDto) throws EmailExistsException {

//		if (usernameExists(accountDto.getUsername())) {
//			throw new EmailExistsException("There is an account with that email address:  + accountDto.getEmail());");
//		}
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User user = new User();
		user.setPassword(accountDto.getPassword());
		//user.setRoles(new HashSet<Role>(getAllRoles()));
		//for(Role role: getAllRoles()) {
		Role role = new Role();
		
		role.setId(Long.parseLong(accountDto.getRole()));
		//	role.getUsers().add(user);
		user.getRoles().add(role);
//		HashSet<User> set = new HashSet<User>();
//			set.add(user);
//			role.setUsers(set);
			//System.out.println(role.getName());
		//}
		
		user.setUsername(accountDto.getUsername());
		
		session.save(user);
		session.getTransaction().commit();
		return user;
	}

	private boolean usernameExists(String username) {
		User user = findByUsername(username);
		if (user != null) {
			return true;
		}
		return false;
	}

	private User findByUsername(String username) {
		Session session = sessionFactory.openSession();
		String hql = "FROM User u WHERE u.username = :uname";
		Query query = session.createQuery(hql);
		query.setParameter("uname", username);
		User u = (User) query.getSingleResult();
		return u;
	}
	public List<Role> getAllRoles() {
		Session session = sessionFactory.openSession();
	    return session.createQuery("SELECT a FROM Role a", Role.class).getResultList();      
	}
}
