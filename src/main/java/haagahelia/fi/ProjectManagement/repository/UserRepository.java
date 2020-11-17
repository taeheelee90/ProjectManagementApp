package haagahelia.fi.ProjectManagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import haagahelia.fi.ProjectManagement.model.user.User;

@RepositoryRestResource
public interface UserRepository extends CrudRepository <User, Long>{

	User findByUsername(String username);

}
