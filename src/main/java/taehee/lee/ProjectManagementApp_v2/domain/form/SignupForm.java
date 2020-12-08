package taehee.lee.ProjectManagementApp_v2.domain.form;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;

/*
 * Signup Form : to Add new User
 * Default Role: USER
 */


@Getter @Data
public class SignupForm {

	@NotEmpty (message = "Please enter username")
	@Size(min = 4, max= 10, message = "Length should be between 4 and 10")
	private String username = "";

	@NotEmpty(message = "Please enter Password")
	@Size(min = 5, max= 20, message = "Length should be between 5 and 20")
	private String password = "";
	
	@NotEmpty (message = "Please enter Email")
	@Email
	private String email;


	/*@NotEmpty
	private String role = "USER";*/

}
