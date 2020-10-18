package haagahelia.fi.ProjectManagement.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import haagahelia.fi.ProjectManagement.model.Project;
import haagahelia.fi.ProjectManagement.model.ProjectStatus;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository repository;
	
	
	@Test
	public void createProject() throws ParseException {
		Project p = new Project ("CRM Implementation", LocalDate.parse("31-08-2020"), LocalDate.parse("31-12-2020"), ProjectStatus.COMPLETE);
		
		repository.save(p);		
		assertThat(repository).isNotNull();		
	}
}
