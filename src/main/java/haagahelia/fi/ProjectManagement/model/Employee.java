package haagahelia.fi.ProjectManagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import haagahelia.fi.ProjectManagement.entity.PersonEntity;
import haagahelia.fi.ProjectManagement.entity.Title;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
@Table(name = "employees")
public class Employee extends PersonEntity {	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Department department;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy ="projectManager")
	private List <Project> projects = new ArrayList <Project>();
	
	@Embedded
	private Contact contact;

	// Constructor
	public Employee() {
	
	}

	
	public Employee(String firstName, String lastName, Title title, Department department, Contact contact) {
		super(firstName, lastName, title);
		this.department = department;
		this.contact = contact;
	
	}



	

}
