package haagahelia.fi.ProjectManagement.model.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import haagahelia.fi.ProjectManagement.model.Employee;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProjectForm {
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	@NotEmpty
	private Employee projectManager;
	
	@NotEmpty
	private int budget;

}
