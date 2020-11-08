package haagahelia.fi.ProjectManagement.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import haagahelia.fi.ProjectManagement.model.Department;
import haagahelia.fi.ProjectManagement.model.Employee;
import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectStatus;
import haagahelia.fi.ProjectManagement.model.project.QProject;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository repository;
	@Autowired
	private EmployeeRepository eRepository;
	@PersistenceContext
	private EntityManager em;
	
	
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
	
	
	@Test
	public void testProjectSearch() throws Exception {
		
			
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QProject project = QProject.project;
		
		// When
		Project result = queryFactory.selectFrom(project).where(project.name.like("%Implementation%"), project.status.eq(ProjectStatus.COMPLETE)).fetchFirst();				
		// Then
		assertThat(result.getName()).isEqualTo("CRM Implementation");
	}
	
}
