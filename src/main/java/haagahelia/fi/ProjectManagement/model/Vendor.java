package haagahelia.fi.ProjectManagement.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import haagahelia.fi.ProjectManagement.model.entity.ObjectEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table (name ="vendor")
public class Vendor extends ObjectEntity {

	@NotEmpty (message = "Please fill Email!")
	private String email;
	
	@NotEmpty (message = "Please fill Phone number")
	private String phone;

	public Vendor() {
		super();
	}

	public Vendor(String name, @NotEmpty(message = "Please fill Email!") String email,
			@NotEmpty(message = "Please fill Phone number") String phone) {
		super(name);
		this.email = email;
		this.phone = phone;
	}

	
}
