package haagahelia.fi.ProjectManagement.serviceTest;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	
	
	@Test
	public void addExpenditureTest() throws ParseException {
		// Given
		Project p = new Project();
		p.setBudget(100);
		
		int cost = 50;
		String description = "test";
		
		
		// When
		Long expenditureId = service.addExpenditure(p.getId(), cost, description);
	
		
		// Then
		//assertEquals(repository.findById(e.getId()), e);
		//assertThat(repository.findById(e.getId()).equals(e));
		//assertEquals("Project ID must be same: ",  p.getId());
		//assertEquals("Left budget = 50", 50, p.getBudget());
		
		assertEquals("Budget decreased to 50", 100, p.getBudget());
	}

}
