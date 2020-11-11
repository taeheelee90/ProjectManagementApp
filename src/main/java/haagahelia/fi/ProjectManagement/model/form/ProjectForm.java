package haagahelia.fi.ProjectManagement.model.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import haagahelia.fi.ProjectManagement.model.Employee;
import haagahelia.fi.ProjectManagement.model.project.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

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