package taehee.lee.ProjectManagementApp_v2.domain.project;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import taehee.lee.ProjectManagementApp_v2.domain.BaseEntity;

/*
 * Expenditure References (file) entity
 */

@Entity
@Getter @Setter
@RequiredArgsConstructor
@Table(name = "expenditure_docs")
public class ExpenditureDocs extends BaseEntity  {

	@Lob
	private byte[] file;
	
	private String fileName;
	
	private String fileType;
	
	@ManyToOne// (fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "expenditure_id")
	private ProjectExpenditure projectExpenditure;

		
	public ExpenditureDocs(byte[] file, String fileName, String fileType, ProjectExpenditure projectExpenditure) {
		super();
		this.file = file;
		this.fileName = fileName;
		this.fileType = fileType;
		this.projectExpenditure = projectExpenditure;
	
	}


}