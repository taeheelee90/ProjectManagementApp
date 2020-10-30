package haagahelia.fi.ProjectManagement.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import haagahelia.fi.ProjectManagement.entity.ObjectEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table (name ="vendor")
public class Vendor extends ObjectEntity {

	private Contact contact;

	public Vendor() {
		super();
	}
		
	public Vendor(String name, Contact contact) {
		super(name);
		this.contact = contact;
	}

	
}
