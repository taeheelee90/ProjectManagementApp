package haagahelia.fi.ProjectManagement.model.entity;

import javax.persistence.Embeddable;

import lombok.Getter;

@Getter
@Embeddable
public class Contact {
	
	private String email;
	private String phone;

	public Contact() {
		super();
	}
	
	
	public Contact(String email, String phone) {
		super();
		this.email = email;
		this.phone = phone;
	}
	
}
