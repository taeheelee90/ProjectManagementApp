package haagahelia.fi.ProjectManagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	private ProjectStatus status;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee projectManager;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department handlingDepartment;

	// Constructor
	public Project() {
		super();
	}

	public Project(String name, Date startDate, Date endDate, ProjectStatus status) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Project(String name, Date startDate, Date endDate, ProjectStatus status, Employee projectManager) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.projectManager = projectManager;
	}

	public Project(String name, Date startDate, Date endDate, ProjectStatus status, Employee projectManager,
			Department handlingDepartment) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.projectManager = projectManager;
		this.handlingDepartment = handlingDepartment;
	}

	// Getter Setter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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

	// toString
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", projectManager=" + projectManager + ", handlingDepartment="
				+ handlingDepartment + "]";
	}

}
