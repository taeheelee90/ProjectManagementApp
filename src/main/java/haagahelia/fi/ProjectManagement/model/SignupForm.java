package haagahelia.fi.ProjectManagement.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import haagahelia.fi.ProjectManagement.entity.PersonEntity;

public class SignupForm extends PersonEntity {
	
	@NotEmpty
	@Size(min = 5, max = 30)
	private String username = "";

	@NotEmpty
	@Size(min = 7, max = 30)
	private String password = "";

	@NotEmpty
	@Size(min = 7, max = 30)
	private String passwordCheck = "";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	
	
	

}
