package haagahelia.fi.ProjectManagement.Entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersonEntity extends BaseEntity {

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;	
	
	public PersonEntity() {};
	
	public PersonEntity(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
}
