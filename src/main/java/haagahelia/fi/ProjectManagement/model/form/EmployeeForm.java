package haagahelia.fi.ProjectManagement.model.form;

import javax.validation.constraints.NotEmpty;
import haagahelia.fi.ProjectManagement.model.Department;
import lombok.Getter;
import lombok.Setter;

/*
 * Employee Form : to Add new Project Manager
 */


@Getter @Setter
public class EmployeeForm {
	
	@NotEmpty (message = "Please enter first name.")
	private String firstName = "";
	
	@NotEmpty (message = "Please enter last name.")
	private String lastName = "";
	
	
	private Department department;
	
	
	@NotEmpty (message="Please enter email.")
	private String email = "";
	
	@NotEmpty (message= "Please enter phone.")
	private String phone = "";

}
