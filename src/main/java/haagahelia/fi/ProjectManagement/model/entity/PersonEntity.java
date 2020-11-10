package haagahelia.fi.ProjectManagement.model.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

/*
 * Person Entity to be extended to person entities
 */


@Getter @Setter
@MappedSuperclass
public class PersonEntity extends BaseEntity {

	@Column(name = "first_name")
	private String firstName;

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
