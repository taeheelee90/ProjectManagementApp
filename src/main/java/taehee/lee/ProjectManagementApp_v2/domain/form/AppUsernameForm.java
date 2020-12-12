package taehee.lee.ProjectManagementApp_v2.domain.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class AppUsernameForm {

	@NotBlank(message = "Please enter nickname.")
	@Length(min = 4, max = 10, message = "Please enter 4-10 characters.")
	private String username;
}
