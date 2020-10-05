package haagahelia.fi.ProjectManagement;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import haagahelia.fi.ProjectManagement.model.Department;
import haagahelia.fi.ProjectManagement.model.Employee;
import haagahelia.fi.ProjectManagement.model.Project;
import haagahelia.fi.ProjectManagement.model.ProjectStatus;
import haagahelia.fi.ProjectManagement.repository.DepartmentRepository;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;

@SpringBootApplication
public class ProjectManagementApplication {

	private Logger log = LoggerFactory.getLogger(ProjectManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner Demo(DepartmentRepository dRepository, EmployeeRepository eRepository,
			ProjectRepository pRepository) {
		return (args) -> {
			log.info("Create Department");
			Department d1 = new Department("Sales");
			Department d2 = new Department("Marketing");
			Department d3 = new Department("IT");

			dRepository.save(d1);
			dRepository.save(d2);
			dRepository.save(d3);

			log.info("create employees");
			Employee e1 = new Employee("James", "Bond", d1);
			Employee e2 = new Employee("Michael", "Jackson", d2);
			Employee e3 = new Employee("Thomas", "Hanks", d3);

			eRepository.save(e1);
			eRepository.save(e2);
			eRepository.save(e3);

			log.info("create projects");
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Project p1 = new Project("CRM Implementation", format.parse("31-08-2020"), format.parse("31-12-2020"),
					ProjectStatus.PROCEEDING, e1, e1.getDepartment());
			Project p2 = new Project("Market analys in Korea", format.parse("01-09-2020"), format.parse("20-09-2020"),
					ProjectStatus.COMPLETE, e2, e2.getDepartment());

			pRepository.save(p1);
			pRepository.save(p2);
		};
	}

}
