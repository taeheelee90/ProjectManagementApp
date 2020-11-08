package haagahelia.fi.ProjectManagement.repositoryTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

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
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProjectRepositoryTest {

	@Autowired
	private ProjectRepository repository;
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
		
		// Given
		Employee e1 = new Employee("Alexander", "Adkins", Department.ACCOUNTING, "aa@email.com", "010111");;
		Project p1 = new Project("CRM Implementation", LocalDate.parse("2019-03-20"), LocalDate.parse("2019-09-15"),
				ProjectStatus.COMPLETE, e1, 10000);
		
		repository.save(p1);
		
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QProject project = QProject.project;
		
		// When
		Project result = queryFactory.selectFrom(project).where(project.name.like("%name%"), project.status.eq(ProjectStatus.COMPLETE)).fetchOne();
				
		// Then
		assertThat(result.getBudget()).isEqualTo(10000);
	}
	
}
