package haagahelia.fi.ProjectManagement.model.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/*
 * Signup Form : to Add new User
 * Default Role: USER
 */


@Getter @Setter
public class SignupForm {

	@NotEmpty (message = "Please enter username")
	@Size(min = 4, max= 10, message = "Length should be between 4 and 10")
	private String username = "";

	@NotEmpty(message = "Please enter Password")
	@Size(min = 5, max= 20, message = "Length should be between 5 and 20")
	private String password = "";

	@NotEmpty(message = "Please re-type Password")
	@Size(min = 5, max= 20, message = "Length should be between 5 and 20")
	private String passwordCheck = "";

	@NotEmpty
	private String role = "USER";

}
