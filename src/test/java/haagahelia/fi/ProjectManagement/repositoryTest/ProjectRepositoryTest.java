package haagahelia.fi.ProjectManagement.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectStatus;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository repository;


	
	
	@Test
	public void createProjectTest() throws ParseException {
		// Given
		Project p = new Project ();
		
		// When
		repository.save(p);		
		
		// Then
		assertThat(repository.findById(p.getId()).equals(p));	
	}

	
	
	@Test
	public void findByNameAndStatusTeat() throws ParseException{
		// Given
		Project p1 = new Project();
		p1.setName("test1");
		p1.setStatus(ProjectStatus.COMPLETE);
		
		Project p2 = new Project();
		p2.setName("test2");
		p2.setStatus(ProjectStatus.WAITING);
		
		repository.save(p1);
		repository.save(p2);
		
		// When
		Collection <Project> result1 = repository.findByNameOrStatus("test", ProjectStatus.COMPLETE);
		Collection <Project> result2 = repository.findByNameOrStatus("test", ProjectStatus.WAITING);
		
		// Then
		assertThat(result1.contains(p1));
		assertThat(result2.contains(p2));
	}

	
}
