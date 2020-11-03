package haagahelia.fi.ProjectManagement.model.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public class PersonEntity extends BaseEntity  {
	
	@NotEmpty (message = "Please fill First name!")
	@Column(name = "first_name")
	private String firstName;
	
	@NotEmpty (message = "Please fill Last name!")
	@Column(name = "last_name")
	private String lastName;	

	
	public PersonEntity() {};
	
	public PersonEntity(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	

	
}
