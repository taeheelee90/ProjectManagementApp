package haagahelia.fi.ProjectManagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import haagahelia.fi.ProjectManagement.entity.ObjectEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "department")
public class Department extends ObjectEntity {

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private List<Employee> employees = new ArrayList<Employee>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "handlingDepartment")
	private List<Project> projects = new ArrayList<Project>();

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

}
