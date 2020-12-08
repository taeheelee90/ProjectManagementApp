package taehee.lee.ProjectManagementApp_v2.domain.form;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import taehee.lee.ProjectManagementApp_v2.domain.employee.Department;

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
