package haagahelia.fi.ProjectManagement.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import haagahelia.fi.ProjectManagement.service.ProjectExpenditureService;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProjectExpenditureServiceTest {

	@Autowired ProjectExpenditureService service;
	@Autowired ProjectExpenditureRepository repository;
	
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void addExpenditureTest() throws ParseException {
		// Given
		Project p = new Project();
		p.setBudget(100);
		ProjectExpenditure e = new ProjectExpenditure();
		
		// When
		service.addExpenditure(p.getId(), 50, "test");
		
		
		// Then
		//assertEquals(repository.findById(e.getId()), e);
		//assertThat(repository.findById(e.getId()).equals(e));
		assertThat(repository.count()== 1);
	}
}
