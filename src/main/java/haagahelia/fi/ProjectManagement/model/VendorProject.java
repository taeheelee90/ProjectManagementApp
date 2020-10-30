package haagahelia.fi.ProjectManagement.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import haagahelia.fi.ProjectManagement.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table (name = "vendr_project")
public class VendorProject extends BaseEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "project_id")
	private Project project;
		
	private int cost;

	public VendorProject() {
		super();
	}

	public VendorProject(Vendor vendor, Project project, int cost) {
		super();
		this.vendor = vendor;
		this.project = project;
		this.cost = cost;
	}
	
	
}
