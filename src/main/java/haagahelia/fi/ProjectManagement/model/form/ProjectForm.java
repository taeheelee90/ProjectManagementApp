package haagahelia.fi.ProjectManagement.model.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import haagahelia.fi.ProjectManagement.model.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectForm {
	
	@NotEmpty
	private String name;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	@NotNull
	private Employee projectManager;
	
	@NotNull
	private int budget;

}