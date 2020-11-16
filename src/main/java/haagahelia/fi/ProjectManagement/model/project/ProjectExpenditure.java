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

/*
 * Project Expenditure entity
 */


@Entity
@Getter @Setter
@RequiredArgsConstructor
@Table(name = "project_expenditure")
public class ProjectExpenditure extends BaseEntity {

	@JsonManagedReference
	@JoinColumn(name = "project_id")
	@ManyToOne (fetch = FetchType.LAZY) //, cascade = CascadeType.PERSIST
	private Project project;

	private int cost;

	private String description;
	
	/*@JsonBackReference	
	@OneToMany (cascade = CascadeType.ALL, mappedBy ="projectExpenditure") // , fetch= FetchType.EAGER
	private Set <ExpenditureDocs> expenditureDocs;*/
	
		
	public ProjectExpenditure(Project project, int cost, String description) {
		super();
		this.project = project;
		this.cost = cost;
		this.description = description;
	}
	
	
	/*
	 * Business Logic:  Adding Expenditure will minus Project budget 
	 */
	
	public ProjectExpenditure createExpenditure (Project project, int cost, String description) {
		ProjectExpenditure expenditure = new ProjectExpenditure();
		
		project.minusBudget(cost);	
	
		return expenditure;
	}




}
