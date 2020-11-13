package haagahelia.fi.ProjectManagement.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import haagahelia.fi.ProjectManagement.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name ="Users")
public class User extends BaseEntity {

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String passwordHash;

	@Column(name = "role", nullable = false)
	private String role;

	public User() {
		super();
	}
	
	public User(String username, String passwordHash, String role) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

}
