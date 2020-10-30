package haagahelia.fi.ProjectManagement.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import haagahelia.fi.ProjectManagement.model.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table (name = "project_expenditure")
public class ProjectExpenditure extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;
	
	private int cost;
	
	private String description;

	public ProjectExpenditure() {
		super();
	}

	public ProjectExpenditure(Project project, int cost, String description) {
		super();
		this.project = project;
		this.cost = cost;
		this.description = description;
	}

}
