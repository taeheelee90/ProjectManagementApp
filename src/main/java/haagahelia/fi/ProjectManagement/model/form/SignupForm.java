package haagahelia.fi.ProjectManagement.model.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupForm {

	@NotEmpty
	@Size(min = 5, max = 30)
	private String username = "";

	@NotEmpty
	@Size(min = 7, max = 30)
	private String password = "";

	@NotEmpty
	@Size(min = 7, max = 30)
	private String passwordCheck = "";

	@NotEmpty
	@Size(min = 7, max = 50)
	private String email = "";

	@NotEmpty
	private String role = "USER";

}
