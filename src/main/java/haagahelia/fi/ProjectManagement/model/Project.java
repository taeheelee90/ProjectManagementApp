package haagahelia.fi.ProjectManagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import haagahelia.fi.ProjectManagement.entity.ObjectEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "projects")
public class Project extends ObjectEntity {

	
	@Column(name = "start_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@Temporal(TemporalType.DATE)
	private LocalDate startDate;

	@Column(name = "end_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//@Temporal(TemporalType.DATE)
	private LocalDate endDate;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private ProjectStatus status;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "project_manager")
	private Employee projectManager;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "handling_department")
	private Department handlingDepartment;

	@Column(name= "vendor_included")
	private String vendorIncluded;
	
	private int budget;
	
	// Constructor		
	public Project() {
		
	}

	public Project(String name, LocalDate startDate, LocalDate endDate, ProjectStatus status, Employee projectManager,
			Department handlingDepartment, String vendorIncluded, int budget) {
		super(name);
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.projectManager = projectManager;
		this.handlingDepartment = handlingDepartment;
		this.vendorIncluded = vendorIncluded;
		this.budget = budget;
	}

	
	


}
