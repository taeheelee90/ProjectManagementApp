package haagahelia.fi.ProjectManagement.model.form;

import javax.validation.constraints.NotEmpty;

import haagahelia.fi.ProjectManagement.model.Department;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmployeeForm {


	@NotEmpty (message = "Please fill First name!")
	private String firstName;
	
	@NotEmpty (message = "Please fill Last name!")
	private String lastName;
	
	@NotEmpty (message = "Please select Department!")
	private Department department;
	
	@NotEmpty (message = "Please fill Email!")
	private String email;
	
	@NotEmpty (message = "Please fill Phone number")
	private String phone;
}
