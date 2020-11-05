package haagahelia.fi.ProjectManagement.serviceTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import haagahelia.fi.ProjectManagement.service.ProjectService;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProjectServiceTest {

	@Autowired ProjectRepository pRepository;
	@Autowired ProjectService service;
	/*
	@Test
	public void updateExpenditureTest() {
		// Given
		Project p = new Project();
		p.setId(100);
		p.setBudget(100);
		pRepository.save(p);
		
		ProjectExpenditure e = new ProjectExpenditure();
		e.setCost(50);
		
		// When
		service.updateBudget(p.getId(), e.getCost());
		
		// Then
		assertEquals("Budget Left should be 50", p.getBudget(), 50);
	}
*/

}
