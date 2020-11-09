package haagahelia.fi.ProjectManagement.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.Project;

@RepositoryRestResource
public interface ProjectRepository extends CrudRepository<Project, Long> { //QuerydslPredicateExecutor<Project>

	/*
	 * List All Projects Order By Project Status 
	 */
	List<Project> findByOrderByStatus();
	
	/*
	 * List All Projects Order By Start DAte
	 */
	List<Project> findByOrderByStartDate();
	
	
	/*
	 * List All Projects Order By End DAte
	 */
	List<Project> findByOrderByEndDate();
	
	/*
	 * List All Projects Order By Name
	 */
	List<Project> findByOrderByName();
	
	
	
	/*	
	 * Search By Project name (any keyword returns related project(s))
	 */
	
	@Query ("SELECT project FROM Project project WHERE lower(project.name) LIKE lower(concat('%', :name,'%'))")
	@Transactional(readOnly = true)
	Collection <Project> findByName(@Param("name") String name);

	

	

}
