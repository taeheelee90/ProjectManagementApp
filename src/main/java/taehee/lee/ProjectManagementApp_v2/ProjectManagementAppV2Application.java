package taehee.lee.ProjectManagementApp_v2;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import taehee.lee.ProjectManagementApp_v2.domain.employee.Department;
import taehee.lee.ProjectManagementApp_v2.domain.employee.Employee;
import taehee.lee.ProjectManagementApp_v2.domain.project.Project;
import taehee.lee.ProjectManagementApp_v2.domain.project.ProjectExpenditure;
import taehee.lee.ProjectManagementApp_v2.domain.project.ProjectStatus;
import taehee.lee.ProjectManagementApp_v2.repository.EmployeeRepository;
import taehee.lee.ProjectManagementApp_v2.repository.ProjectExpenditureRepository;
import taehee.lee.ProjectManagementApp_v2.repository.ProjectRepository;

@SpringBootApplication
@Slf4j
public class ProjectManagementAppV2Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementAppV2Application.class, args);
	}

	
	@Bean
	public CommandLineRunner Demo(EmployeeRepository eRepository, ProjectRepository pRepository,
			ProjectExpenditureRepository peRepository) {

		return (args) -> {

			log.info("create Employees");
			Employee e1 = new Employee("Alexander", "Adkins", Department.ACCOUNTING, "aa@email.com", "010111");
			Employee e2 = new Employee("Roan", "Jackson", Department.HR, "rj@email.com", "010222");
			Employee e3 = new Employee("Izabela", "Bostock", Department.IT, "ib@email.com", "010333");
			Employee e4 = new Employee("Helen", "Leary", Department.MARKETING, "hl@email.com", "010444");
			Employee e5 = new Employee("Linda", "Douglas", Department.PROCUREMENT, "ld@email.com", "010555");
			Employee e6 = new Employee("Rafael", "Ortega", Department.SALES, "ro@email.com", "010666");

			eRepository.save(e1);
			eRepository.save(e2);
			eRepository.save(e3);
			eRepository.save(e4);
			eRepository.save(e5);
			eRepository.save(e6);

			log.info("create new projects");
			// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Project p1 = new Project("CRM Implementation", LocalDate.parse("2019-03-20"), LocalDate.parse("2019-09-15"),
					ProjectStatus.COMPLETE, e5, 0);
			Project p2 = new Project("Data Migration", LocalDate.parse("2020-10-10"), LocalDate.parse("2020-12-31"),
					ProjectStatus.PROCEEDING, e3, 30000);
			Project p3 = new Project("Q4 profit analysis", LocalDate.parse("2021-01-01"), LocalDate.parse("2021-02-20"),
					ProjectStatus.WAITING, e1, 15000);
			Project p4 = new Project("Data Security Policy Update", LocalDate.parse("2021-02-10"),
					LocalDate.parse("2021-03-31"), ProjectStatus.WAITING, e3, 2000);
			Project p5 = new Project("Asian Market Analysis", LocalDate.parse("2020-06-05"),
					LocalDate.parse("2020-12-31"), ProjectStatus.PROCEEDING, e4, 3000);

			pRepository.save(p1);
			pRepository.save(p2);
			pRepository.save(p3);
			pRepository.save(p4);
			pRepository.save(p5);

			log.info("create expenditures");

			ProjectExpenditure pe1 = new ProjectExpenditure(p1, 500, "Initial Cost");
			ProjectExpenditure pe2 = new ProjectExpenditure(p1, 5000, "License Fee");
			ProjectExpenditure pe3 = new ProjectExpenditure(p1, 3000, "Migration Fee");

			peRepository.save(pe1);
			peRepository.save(pe2);
			peRepository.save(pe3);

			/*log.info("create users");
			// user/user, admin/admin
			// email for testing
			String email = "bgm173@myy.haaga-helia.fi";
			User user1 = new User("user", "$2a$10$zTvn5SMAQ.NSO/jnY3PjAueq2qEKG1gVLXASHCob6q6.EpBDj90H6", email, "USER");
			User user2 = new User("admin", "$2a$10$Qflab20ugY5RFyC65P.2VuqGPGLvlJEuzGOqZsB21j9j8tJlbcrGe", email,  "ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);*/

		};
	}
}
