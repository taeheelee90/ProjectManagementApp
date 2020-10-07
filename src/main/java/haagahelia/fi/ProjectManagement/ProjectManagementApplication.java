package haagahelia.fi.ProjectManagement;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import haagahelia.fi.ProjectManagement.entity.Title;
import haagahelia.fi.ProjectManagement.model.Department;
import haagahelia.fi.ProjectManagement.model.Employee;
import haagahelia.fi.ProjectManagement.model.Project;
import haagahelia.fi.ProjectManagement.model.ProjectStatus;
import haagahelia.fi.ProjectManagement.model.Role;
import haagahelia.fi.ProjectManagement.model.User;
import haagahelia.fi.ProjectManagement.repository.DepartmentRepository;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import haagahelia.fi.ProjectManagement.repository.UserRepository;

@SpringBootApplication
public class ProjectManagementApplication {

	private Logger log = LoggerFactory.getLogger(ProjectManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner Demo(DepartmentRepository dRepository, EmployeeRepository eRepository,
			ProjectRepository pRepository, UserRepository uRepository) {
		return (args) -> {
			log.info("Create Department");
			Department d1 = new Department("Sales");
			Department d2 = new Department("Marketing");
			Department d3 = new Department("IT");

			dRepository.save(d1);
			dRepository.save(d2);
			dRepository.save(d3);

			log.info("create employees");
			Employee e1 = new Employee("Alexander", "Adkins", Title.MANAGER, d1);
			Employee e2 = new Employee("Roan", "Jackson", Title.MANAGER, d2);
			Employee e3 = new Employee("Izabela", "Bostock", Title.CLERK, d3);

			eRepository.save(e1);
			eRepository.save(e2);
			eRepository.save(e3);

			log.info("create projects");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Project p1 = new Project("CRM Implementation", format.parse("2020-08-30"), format.parse("2020-12-31"),
					ProjectStatus.PROCEEDING, e1, e1.getDepartment());
			Project p2 = new Project("Market analys in Korea", format.parse("2020-09-01"), format.parse("2020-09-20"),
					ProjectStatus.COMPLETE, e2, e2.getDepartment());

			pRepository.save(p1);
			pRepository.save(p2);

			log.info("create users");
			// id: jdadmin pw: admin
			User u1 = new User("Jedda", "Boyce", Title.DIRECTOR, Role.ADMIN, "jbadmin",
					"$2a$10$Qflab20ugY5RFyC65P.2VuqGPGLvlJEuzGOqZsB21j9j8tJlbcrGe");
			// id: aaadmin pw: admin
			User u2 = new User("Alexander", "Adkins", Title.MANAGER, Role.ADMIN, "aaadmin",
					"$2a$10$Qflab20ugY5RFyC65P.2VuqGPGLvlJEuzGOqZsB21j9j8tJlbcrGe");
			// id: ibuser pw:user
			User u3 = new User("Izabela", "Bostock", Title.ASSOCIATE, Role.USER, "ibuser",
					"$2a$10$zTvn5SMAQ.NSO/jnY3PjAueq2qEKG1gVLXASHCob6q6.EpBDj90H6");

			uRepository.save(u1);
			uRepository.save(u2);
			uRepository.save(u3);
		};
	}

}
