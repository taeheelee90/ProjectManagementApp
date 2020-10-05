package haagahelia.fi.ProjectManagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import haagahelia.fi.ProjectManagement.model.Department;

@RepositoryRestResource
public interface DepartmentRepository extends CrudRepository <Department, Long> {

}
