package haagahelia.fi.ProjectManagement.repositoryTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import haagahelia.fi.ProjectManagement.system.NotEnoughBudgetException;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProjectExpenditirueRepositoryTest {

	@Autowired
	ProjectExpenditureRepository peRepository;
	@Autowired
	ProjectRepository pRepository;
	@Autowired
	EmployeeRepository eRepository;

	@Test
	public void addProjectExpentirue() throws ParseException {
		// Given
		ProjectExpenditure projectExpenditure = new ProjectExpenditure();
		Project project = new Project();
		project.setBudget(100);
		

		// When
		projectExpenditure.createExpenditure(project, 50, "Test");

		// Then
		assertEquals("Budget Left should be 50", project.getBudget(), 50);
	}

	@Test(expected = NotEnoughBudgetException.class)
	public void exceedeProjectBudege() throws ParseException {

		// Given
		ProjectExpenditure projectExpenditure = new ProjectExpenditure();
		Project project = new Project();
		project.setBudget(100);

		// When
		projectExpenditure.createExpenditure(project, 150, "Test");

		// Then
		fail("Should throw an exception");

	}
}
