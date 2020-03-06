package services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import entities.User;

public class AuthService {
	
	private SessionFactory sessionFactory;
    //private HibernateTemplate hibernateTemplate;
    private static Logger log = Logger.getLogger(AuthService.class);
 
    private AuthService() { }
 
//    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//        this.hibernateTemplate = hibernateTemplate;
//    }
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @SuppressWarnings( { "unchecked", "deprecation" } )
    public boolean findUser(String uname, String upwd) {
        log.info("Checking the user in the database");
        boolean isValidUser = false;
        //String sqlQuery = "from User u where u.username=? and u.password=?";
        try {
        	//SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
        	Session session = sessionFactory.openSession();
        	
        	String hql = "FROM User u WHERE u.username = :uname and u.password=:upwd";
        	Query query = session.createQuery(hql);
        	query.setParameter("uname",uname);
        	query.setParameter("upwd",upwd);
        	List<User> userObj = query.list();
        	if(userObj != null && userObj.size() > 0) {
        		isValidUser = true;
        	}
//        	User u =  (User)session.get(User.class, 1);
//        	System.out.println(u.getName());
//            List<User> userObj = (List<User>) hibernateTemplate.find(sqlQuery, uname, upwd);
//            //System.out.println(userObj.le);
//            if(userObj != null && userObj.size() > 0) {
//                log.info("Id= " + userObj.get(0).getId() + ", Name= " + userObj.get(0).getName() + ", Password= " + userObj.get(0).getPassword());
//                isValidUser = true;
//            }
        } catch(Exception e) {
            isValidUser = false;
            log.error("An error occurred while fetching the user details from the database", e);    
        }
        return isValidUser;
    }
}
