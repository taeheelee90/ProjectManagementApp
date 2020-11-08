package haagahelia.fi.ProjectManagement.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectStatus;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepositorySupport;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository repository;
	
	@Autowired
	private ProjectRepositorySupport support;

	
	
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
	public void testProjectSearch() throws Exception {
	
		
		// When
		List<Project> result = support.search("Implementation", ProjectStatus.COMPLETE); 
				
		// Then
		assertThat(result.size()==1);
		assertThat(result.get(0).getBudget() == 0);
	}
	
}
