package haagahelia.fi.ProjectManagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import haagahelia.fi.ProjectManagement.model.User;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long> {
	
	//List<User> findByRole (String role);
	User findByUsername (String username);

}
