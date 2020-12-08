package taehee.lee.ProjectManagementApp_v2.domain.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import taehee.lee.ProjectManagementApp_v2.domain.employee.Employee;
import taehee.lee.ProjectManagementApp_v2.domain.project.ProjectStatus;

/*
 * Project Form : to Add new Project
 */


@Getter @Setter
public class ProjectForm {
	
	@NotEmpty (message = "Please enter name.")
	private String name;
	
	@NotNull (message = "Please select start date.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@NotNull (message = "Please select end date.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	@NotNull
	private Employee projectManager;
	
	@NotNull (message = "Please enter budget")
	private int budget;
	
	private ProjectStatus projectStatus;

}