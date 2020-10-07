package haagahelia.fi.ProjectManagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import haagahelia.fi.ProjectManagement.entity.PersonEntity;
import haagahelia.fi.ProjectManagement.entity.Title;


@Entity
@Table(name = "employee")
public class Employee extends PersonEntity {	

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy ="projectManager")
	private List <Project> projects;

	// Constructor
	public Employee() {
	
	}
	
	public Employee(String firstName, String lastName, Title title) {
		super(firstName, lastName, title);
	}

	public Employee(String firstName, String lastName, Title title, Department department) {
		super(firstName, lastName, title);
		this.department = department;
	}
	
	public Employee(String firstName, String lastName, Title title, Department department, List<Project> projects) {
		super(firstName, lastName, title);
		this.department = department;
		this.projects = projects;
	}

	// Getters Setters	
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	

}
