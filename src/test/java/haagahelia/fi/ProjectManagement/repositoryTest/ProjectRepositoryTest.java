package haagahelia.fi.ProjectManagement.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository repository;
	
	
	@Test
	public void createProject() throws ParseException {
		// Given
		Project p = new Project ();
		
		// When
		repository.save(p);		
		
		// Then
		assertThat(repository.findById(p.getId()).equals(p));	
	}
	
	
	@Test
	public void cancelProject () throws ParseException {
		// Given
		Project p = new Project ();
		
		// When
		repository.save(p);
		repository.deleteById(p.getId());
		
		// Then
		assertThat(repository.findById(p.getId()).equals(null));
		}
	
}
