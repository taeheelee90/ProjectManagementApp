package haagahelia.fi.ProjectManagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import haagahelia.fi.ProjectManagement.model.entity.PersonEntity;
import haagahelia.fi.ProjectManagement.model.project.Project;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
@Table(name = "employees")
public class Employee extends PersonEntity {
	
	@Column(name = "department")
	@Enumerated(EnumType.STRING)
	@NotEmpty (message = "Please select Department!")
	private Department department;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy ="projectManager")
	private List <Project> projects = new ArrayList <Project>();
	
	@NotEmpty (message = "Please fill Email!")
	private String email;
	
	@NotEmpty (message = "Please fill Phone number")
	private String phone;

	// Constructor
	public Employee() {
	
	}

	public Employee(String firstName, String lastName, @NotEmpty(message = "Please select Department!") Department department,
			@NotEmpty(message = "Please fill Email!") String email,
			@NotEmpty(message = "Please fill Phone number") String phone) {
		super(firstName, lastName);
		this.department = department;		
		this.email = email;
		this.phone = phone;
	}
	

}
