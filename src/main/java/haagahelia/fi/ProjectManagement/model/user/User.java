package haagahelia.fi.ProjectManagement.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;

import haagahelia.fi.ProjectManagement.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User extends BaseEntity {

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String passwordHash;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "role", nullable = false)
	private String role;

	public User() {
		super();
	}
	
	public User(String username, String passwordHash, String email, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.role = role;
	}

}
