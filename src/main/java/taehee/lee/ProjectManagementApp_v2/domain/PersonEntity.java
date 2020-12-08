package taehee.lee.ProjectManagementApp_v2.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;


import lombok.Getter;
import lombok.Setter;

/*
 * Person Entity to be extended to person entities
 */


@Getter @Setter
@MappedSuperclass
public class PersonEntity extends BaseEntity {

	@NotEmpty (message = "Please enter first name.")
	@Column(name = "first_name")
	private String firstName;
	
	@NotEmpty (message = "Please enter last name.")
	@Column(name = "last_name")
	private String lastName;

	public PersonEntity() {
	};

	public PersonEntity(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;

	}

}

