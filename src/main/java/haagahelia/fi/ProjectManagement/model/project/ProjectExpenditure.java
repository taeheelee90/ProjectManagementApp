package haagahelia.fi.ProjectManagement.model.project;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import haagahelia.fi.ProjectManagement.model.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@Table(name = "project_expenditure")
public class ProjectExpenditure extends BaseEntity {


	@ManyToOne(fetch = FetchType.LAZY)
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

	
	
	// Business Logic:  Adding Expenditure will minus Project budget (if cost> budget, error message)
	public static void addExpenditure (Project project, int cost, String description) {
		ProjectExpenditure e = new ProjectExpenditure(project, cost, description);
		e.getProject().addExpenditure(cost);
	}


}
