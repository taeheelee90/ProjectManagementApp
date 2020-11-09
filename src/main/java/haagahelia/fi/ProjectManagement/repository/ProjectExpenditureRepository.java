package haagahelia.fi.ProjectManagement.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;

@RepositoryRestResource
public interface ProjectExpenditureRepository extends CrudRepository <ProjectExpenditure, Long> {
	
	@Transactional (readOnly = true)
	Collection <ProjectExpenditure> findByProjectId(Long projectId);
	
	
	

}
