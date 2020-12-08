package taehee.lee.ProjectManagementApp_v2.domain.employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;
import taehee.lee.ProjectManagementApp_v2.domain.PersonEntity;
import taehee.lee.ProjectManagementApp_v2.domain.project.Project;

@Entity
@Getter @Setter
@Table(name = "employees")
public class Employee extends PersonEntity {

	@Column(name = "department")
	@Enumerated(EnumType.STRING)
	private Department department;

	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "projectManager", fetch = FetchType.EAGER)
	private Set<Project> projects;

	@NotEmpty (message="Please enter email.")
	private String email;

	@NotEmpty (message= "Please enter phone.")
	private String phone;

	/*
	 * Constructor
	 */
	public Employee() {

	}

	public Employee(String firstName, String lastName, Department department, String email, String phone) {
		super(firstName, lastName);
		this.department = department;
		this.email = email;
		this.phone = phone;
	}

	/*
	 * Relationship Management with Project
	 */
	protected Set<Project> getProjectsInternal() {
		if (this.projects == null) {
			this.projects = new HashSet<>();
		}

		return this.projects;
	}

	protected void setProjectsInternal(Set<Project> projects) {
		this.projects = projects;
	}

	public List<Project> getProjects() {
		List<Project> sortedProjects = new ArrayList<>(getProjectsInternal());
		PropertyComparator.sort(sortedProjects, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedProjects);
	}

	// Project Add -> Set ProjectManager
	public void addProject(Project project) {
		if (projects.size() == 0) {
			getProjectsInternal().add(project);
		}
		project.setProjectManager(this);
	}

	// Return Project with project name or null
	public Project getProject(String name) {
		return getProject(name, false);
	}

	private Project getProject(String name, boolean ignore) {
		name = name.toLowerCase();
		for (Project project : getProjectsInternal()) {
			if (!ignore || projects.size() != 0) {
				String resultName = project.getName();
				if (resultName.equals(name)) {
					return project;
				}
			}
		}
		return null;
	}
}