package haagahelia.fi.ProjectManagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;

@RepositoryRestResource
public interface ProjectExpenditureRepository extends CrudRepository <ProjectExpenditure, Long> {

}
