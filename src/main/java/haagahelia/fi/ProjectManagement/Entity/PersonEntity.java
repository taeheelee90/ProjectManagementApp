package haagahelia.fi.ProjectManagement.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersonEntity extends BaseEntity  {

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;	

	@Column(name = "title")
	@Enumerated(EnumType.STRING)
	private Title title;
	
	public PersonEntity() {};
	
	public PersonEntity(String firstName, String lastName, Title title) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
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

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	
}
