package haagahelia.fi.ProjectManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import haagahelia.fi.ProjectManagement.model.project.Project;

@RepositoryRestResource
public interface ProjectRepository extends CrudRepository<Project, Long> {

	

}
