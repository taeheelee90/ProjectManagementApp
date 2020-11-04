package haagahelia.fi.ProjectManagement.model.project;

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

import haagahelia.fi.ProjectManagement.exception.NotEnoughBudgetException;
import haagahelia.fi.ProjectManagement.model.Employee;
import haagahelia.fi.ProjectManagement.model.entity.ObjectEntity;
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
	
	@ManyToOne //(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_manager")
	private Employee projectManager;

	private int budget;
	
	// Constructor		
	public Project() {
		
	}

	public Project(String name, LocalDate startDate, LocalDate endDate, ProjectStatus status, Employee projectManager,
			int budget) {
		super(name);
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.projectManager = projectManager;
		this.budget = budget;
	}

	
	// Business Logic:  Adding Expenditure will minus Project budget (if cost > budget, throw exception)
	public void addExpenditure(int expenditure) {
		int leftBudget = this.budget -= expenditure;
		
		/*if(leftBudget < 0) {
			throw new NotEnoughBudgetException ("Budget is not enough");
		}*/
	
		this.budget = leftBudget;
	}

}
