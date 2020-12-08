package taehee.lee.ProjectManagementApp_v2.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import taehee.lee.ProjectManagementApp_v2.domain.project.ProjectExpenditure;



@RepositoryRestResource
public interface ProjectExpenditureRepository extends CrudRepository <ProjectExpenditure, Long> {
	
	@Transactional (readOnly = true)
	Collection <ProjectExpenditure> findByProjectId(Long projectId);
	
	
	

}

