package haagahelia.fi.ProjectManagement.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectStatus;

@RepositoryRestResource
public interface ProjectRepository extends CrudRepository<Project, Long>, QuerydslPredicateExecutor<Project> {

	/*	
	 * Search By Project name (any keyword returns related project(s))
	 */
	//@Query ("SELECT project FROM Project project WHERE lower(project.name) LIKE %:name% ")
	@Query ("SELECT project FROM Project project WHERE lower(project.name) LIKE lower(concat('%', :name,'%'))")
	@Transactional(readOnly = true)
	Collection <Project> findByName(@Param("name") String name);
	
	/*	
	 * Search By Project Status 
	 */
	
	@Query ("SELECT project FROM Project project WHERE project.status =:status")
	@Transactional(readOnly = true)
	Collection <Project> findByStatus(@Param("status") ProjectStatus status);

	
	/*
	 * Search By Name & Status
	 */

	
	

	

}
