package haagahelia.fi.ProjectManagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import haagahelia.fi.ProjectManagement.entity.ObjectEntity;

@Entity
@Table(name = "department")
public class Department extends ObjectEntity {


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Employee> employees;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="handlingDepartment")
	private List<Project> projects;
	
	// Constructor
	public Department() {				
	}
	
	
	public Department(String name) {
		super(name);
		
	}

	public Department(String name, List<Employee> employees) {
		super(name);
		this.employees = employees;
	}
	
	
	public Department(String name, List<Employee> employees, List<Project> projects) {
		super(name);
		this.employees = employees;
		this.projects = projects;
	}

	// Getters Setters
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
		
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
}
