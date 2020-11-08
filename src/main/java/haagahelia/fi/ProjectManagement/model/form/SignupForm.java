package haagahelia.fi.ProjectManagement.model.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupForm {

	@NotEmpty
	private String username = "";

	@NotEmpty
	private String password = "";

	@NotEmpty
	private String passwordCheck = "";

	@NotEmpty
	private String email = "";

	@NotEmpty
	private String role = "USER";

}
