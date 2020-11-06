package haagahelia.fi.ProjectManagement.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;

@RepositoryRestResource
public interface ProjectExpenditureRepository extends CrudRepository <ProjectExpenditure, Long> {
	
	//@Query("SELECT e FROM ProjectExpenditure e left join fetch e.project WHERE e.project LIKE :projectId")
	@Transactional (readOnly = true)
	Collection <ProjectExpenditure> findByProjectId(@Param("proojectId") Long projectId );
	
	/*
	 * @Query("SELECT DISTINCT owner FROM Owner owner left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
	@Transactional(readOnly = true)
	Collection<Owner> findByLastName(@Param("lastName") String lastName);

	 * 
	 * 
	 * */
}
