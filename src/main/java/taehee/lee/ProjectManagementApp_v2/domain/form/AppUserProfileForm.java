package taehee.lee.ProjectManagementApp_v2.domain.form;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class AppUserProfileForm {

	@Length(max = 50, message = "Too long description.")
	private String description;

	@Length(max = 20, message = "Too long phone number")
	private String phone;

	private String profileImage;
	
	
}
