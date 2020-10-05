package haagahelia.fi.ProjectManagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Employee> employees;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="handlingDepartment")
	private List<Project> projects;
	
	// Constructor
	public Department() {

	}

	public Department(String name) {
		super();
		this.name = name;
	}

	public Department(String name, List<Employee> employees) {
		super();
		this.name = name;
		this.employees = employees;
	}
	
	
	public Department(String name, List<Employee> employees, List<Project> projects) {
		super();
		this.name = name;
		this.employees = employees;
		this.projects = projects;
	}

	// Getters Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


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

	// toString method
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", employees=" + employees + ", projects=" + projects + "]";
	}

	
}
