package haagahelia.fi.ProjectManagement.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import haagahelia.fi.ProjectManagement.entity.Title;
import haagahelia.fi.ProjectManagement.model.Role;
import haagahelia.fi.ProjectManagement.model.User;
import haagahelia.fi.ProjectManagement.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Test
	public void createUserTest() {
		// id: kcadmin pw:admin
		User user = new User("Kristina", "Cassidy",Title.DIRECTOR, Role.ADMIN, "kcadmin",
				"$2a$10$Qflab20ugY5RFyC65P.2VuqGPGLvlJEuzGOqZsB21j9j8tJlbcrGe");
		repository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	/*@Test
	public void findByRoleTest() {
		User user = new User("Kristina", "Cassidy", Role.DIRECTOR, "kcadmin",
				"$2a$10$Qflab20ugY5RFyC65P.2VuqGPGLvlJEuzGOqZsB21j9j8tJlbcrGe");
		repository.save(user);
		
		List<User> result = repository.findByRole(user.getRole());
		
		assertThat(result).hasSize(1);
		assertThat(result).contains(user);
	}*/
	
	
	@Test
	public void findByUserNameTest() {
		User user = repository.findByUsername("jbadmin");
		assertThat(user.getRole()).isEqualTo(Role.ADMIN.name());
		
	}


}
