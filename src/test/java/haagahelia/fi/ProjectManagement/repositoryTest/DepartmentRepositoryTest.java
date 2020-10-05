package haagahelia.fi.ProjectManagement.repositoryTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import haagahelia.fi.ProjectManagement.model.Department;
import haagahelia.fi.ProjectManagement.repository.DepartmentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository repository;
	
	@Test
	public void createDepartmentTest() {
		Department d = new Department ("Customer Services");
		repository.save(d);
		assertThat(repository).isNotNull();
	}
}
