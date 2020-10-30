package haagahelia.fi.ProjectManagement.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import haagahelia.fi.ProjectManagement.model.Department;
import haagahelia.fi.ProjectManagement.model.Employee;
import haagahelia.fi.ProjectManagement.model.Title;
import haagahelia.fi.ProjectManagement.model.entity.Contact;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@Test
	public void createEmployeeTest() {
		Department d = new Department("IT");
		Contact c = new Contact ("a@email.com", "1111");

		Employee e = new Employee("Tei", "Lee", Title.DIRECTOR, d, c);
		
		repository.save(e);
		assertThat(repository).isNotNull();
	}
}
