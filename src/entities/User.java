package entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "tblUsers")
public class User implements Serializable {
 
    private static final long serialVersionUID = 1L;
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
    private int id;
    
    @Column(name = "email", length = 50, nullable = false)
    private String username;
       
    @Column(name = "password", length = 50, nullable = false)
    private String password;
    
//    @Column(name = "role", length = 50, nullable = false)
//    private String role;
    
    @ManyToMany
    @JoinTable(name="tblUserRoles", joinColumns= {@JoinColumn(name="user_id")},  inverseJoinColumns= {@JoinColumn(name="role_id")})
    private Set<Role> roles;
    
 
    @Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}

	public User() {
		super();
		this.roles = new HashSet<Role>();
	}

	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.roles = new HashSet<Role>();
//		this.role = role;
	}

	public long getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
 
    public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
        this.password = password;
    }

//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
	
	public boolean match(String name, String password) {
        return this.username.equals(name) && this.password.equals(password);
    }
}