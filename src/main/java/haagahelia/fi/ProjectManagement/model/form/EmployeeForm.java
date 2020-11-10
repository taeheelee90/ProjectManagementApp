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
	
	@NotEmpty
	private String firstName = "";
	
	@NotEmpty
	private String lastName = "";
	
	
	private Department department;
	
	
	@NotEmpty
	private String email = "";
	
	@NotEmpty
	private String phone = "";

}
