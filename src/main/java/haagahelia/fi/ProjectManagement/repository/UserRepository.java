package haagahelia.fi.ProjectManagement.repository;

import org.springframework.data.repository.CrudRepository;

import haagahelia.fi.ProjectManagement.model.user.User;

public interface UserRepository extends CrudRepository <User, Long> {

	User findByUsername(String username);
}
