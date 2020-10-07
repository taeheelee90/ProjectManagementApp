package haagahelia.fi.ProjectManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import haagahelia.fi.ProjectManagement.entity.PersonEntity;
import haagahelia.fi.ProjectManagement.entity.Title;


@Entity
@Table(name = "user")
public class User extends PersonEntity {
	
	@Column(name = "user_name", nullable = false, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String passwordHash;
	
	@Column(name = "role", nullable = false)
	private Role role;
	
	public User() {

	}
	
	public User(String firstname, String lastname, Title title) {
		super(firstname, lastname, title);
		
	}	

	public User(String firstname, String lastname, Title title, Role role, String username, String passwordHash) {
		super(firstname, lastname, title);
		this.role = role;
		this.username = username;
		this.passwordHash = passwordHash;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
		

}
