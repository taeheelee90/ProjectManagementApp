package taehee.lee.ProjectManagementApp_v2.domain.form;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/*
 * Signup Form : to Add new User
 * Default Role: USER
 */


@Data
public class SignUpForm {

	@NotBlank 
	@Size(min = 4, max= 10, message = "Length should be between 4 and 10")
	private String username;

	@NotBlank
	@Size(min = 5, max= 20, message = "Length should be between 5 and 20")
	private String password;
	
	@NotBlank 
	@Email
	private String email;

}
