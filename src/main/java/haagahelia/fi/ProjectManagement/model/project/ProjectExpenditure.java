package haagahelia.fi.ProjectManagement.model.project;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import haagahelia.fi.ProjectManagement.model.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@Table(name = "project_expenditure")
public class ProjectExpenditure extends BaseEntity {


	@ManyToOne (fetch = FetchType.LAZY) //, cascade = CascadeType.PERSIST
	@JsonManagedReference
	@JoinColumn(name = "project_id")
	private Project project;

	private int cost;

	private String description;


	public ProjectExpenditure(Project project, int cost, String description) {
		super();
		this.project = project;
		this.cost = cost;
		this.description = description;
	}

	
	
	// Add Expenditure: Adding Expenditure will minus Project budget 
	public ProjectExpenditure createExpenditure (Project project, int cost, String description) {
		ProjectExpenditure expenditure = new ProjectExpenditure();
		
		project.minusBudget(cost);	
	
		return expenditure;
	}



}
