package haagahelia.fi.ProjectManagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import haagahelia.fi.ProjectManagement.Entity.PersonEntity;

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
	
	public Employee(String firstName, String lastName) {
		super(firstName, lastName);
	}

	public Employee(String firstName, String lastName, Department department) {
		super(firstName, lastName);
		this.department = department;
	}
	
	public Employee(String firstName, String lastName, Department department, List<Project> projects) {
		super(firstName, lastName);
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
