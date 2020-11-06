package haagahelia.fi.ProjectManagement.model.project;

import javax.persistence.CascadeType;
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


	@ManyToOne (fetch = FetchType.LAZY) //, cascade = CascadeType.PERSIST
	@JoinColumn(name = "project_id")
	private Project project;

	private int cost;

	private String description;

	
	
	// Add Expenditure: Adding Expenditure will minus Project budget 
	public ProjectExpenditure createExpenditure (Project project, int cost, String description) {
		ProjectExpenditure expenditure = new ProjectExpenditure();
		
		/*expenditure.setProject(project);
		expenditure.setCost(cost);
		expenditure.setDescription(description);*/
		
		project.minusBudget(cost);	
	
		return expenditure;
	}


}
