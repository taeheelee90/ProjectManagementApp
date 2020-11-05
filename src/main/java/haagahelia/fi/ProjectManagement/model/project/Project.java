package haagahelia.fi.ProjectManagement.model.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
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
	@JoinColumn(name = "project_manager_id")
	private Employee projectManager;

	private int budget;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy ="project", fetch = FetchType.EAGER)
	private Set <ProjectExpenditure> projectExpenditures;
	
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

	// Relationship Management with Project Expenditure
	protected Set <ProjectExpenditure> getProjectExpendituresInternal(){
		if(this.projectExpenditures == null) {
			this.projectExpenditures = new HashSet<>();
		}
		
		return this.projectExpenditures;
	}
	
	
	protected void setProjectExpendituresInternal (Set <ProjectExpenditure> projectExpenditures) {
		this.projectExpenditures = projectExpenditures;
	}
	
	public List <ProjectExpenditure> getProjectExpenditures(){
		List<ProjectExpenditure> sortedProjectExpenditures = new ArrayList<>(getProjectExpendituresInternal());
		PropertyComparator.sort(sortedProjectExpenditures, new MutableSortDefinition("cost", true, true));
		return Collections.unmodifiableList(sortedProjectExpenditures);
	}
	
	
	// Project Expenditure Add -> Set Project
	public void addProjectExpenditure(ProjectExpenditure projectExpenditure) {
		if(projectExpenditures.size() == 0) {
			getProjectExpendituresInternal().add(projectExpenditure);
		}
		projectExpenditure.setProject(this);
	}
	
	
	// Return Project Expenditures(cost, description) or null
	public ProjectExpenditure getProjectExpenditure (int cost, String description) {
		return getProjectExpenditure(cost, description, false);
	}
	
	
	private ProjectExpenditure getProjectExpenditure(int cost, String description, boolean ignore) {
		for(ProjectExpenditure projectExpenditure: getProjectExpendituresInternal()) {
			if(!ignore || projectExpenditures.size() != 0) {
				int resultCost = projectExpenditure.getCost();
				String resultDesc = projectExpenditure.getDescription();
				if(resultCost == cost && resultDesc.equals(description)) {
					return projectExpenditure;
				}
			}
		}
		return null;
	}

	
	

	// Business Logic:  Adding Expenditure will minus Project budget (if cost > budget, throw exception)
	public void minusBudget(int expenditure) {
		int leftBudget = this.budget -= expenditure;
		
		if(leftBudget <= 0) {
			throw new NotEnoughBudgetException ("Budget is not enough");
		}
	
		this.budget = leftBudget;
	}

}
