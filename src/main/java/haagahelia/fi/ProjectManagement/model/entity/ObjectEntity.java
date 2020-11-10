package haagahelia.fi.ProjectManagement.model.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

/*
 * Object Entity to be extended to non-human entities
 */


@Getter @Setter
@MappedSuperclass
public class ObjectEntity extends BaseEntity {
	
	@Column(name = "name")
	private String name;
	
	public ObjectEntity() {
		
	}
		
	public ObjectEntity(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
