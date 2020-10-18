package haagahelia.fi.ProjectManagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import haagahelia.fi.ProjectManagement.entity.ObjectEntity;

@Entity
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
	
	//@Lob
	//private String description;

	@ManyToOne
	@JoinColumn(name = "project_manager")
	private Employee projectManager;

	@ManyToOne
	@JoinColumn(name = "handling_department")
	private Department handlingDepartment;

	// Constructor		
	public Project() {
		
	}

	public Project(String name, LocalDate startDate, LocalDate endDate, ProjectStatus status) {
		super(name);
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Project(String name, LocalDate startDate, LocalDate endDate, ProjectStatus status, Employee projectManager) {
		super(name);
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.projectManager = projectManager;
	}

	public Project(String name, LocalDate startDate, LocalDate endDate, ProjectStatus status, Employee projectManager,
			Department handlingDepartment) {
		super(name);
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.projectManager = projectManager;
		this.handlingDepartment = handlingDepartment;
	}

	// Getter Setter
		public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public Employee getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(Employee projectManager) {
		this.projectManager = projectManager;
	}

	public Department getHandlingDepartment() {
		return handlingDepartment;
	}

	public void setHandlingDepartment(Department handlingDepartment) {
		this.handlingDepartment = handlingDepartment;
	}
	

}
