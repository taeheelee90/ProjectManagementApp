package taehee.lee.ProjectManagementApp_v2.domain.form;

import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PasswordForm {

	@Size(min = 5, max= 20, message = "Length should be between 5 and 20")
	private String newPassword;
	
	@Size(min = 5, max= 20, message = "Length should be between 5 and 20")
	private String newPasswordConfirm;
}
