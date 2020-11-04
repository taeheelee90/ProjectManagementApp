package haagahelia.fi.ProjectManagement;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import haagahelia.fi.ProjectManagement.model.Department;
import haagahelia.fi.ProjectManagement.model.Employee;
import haagahelia.fi.ProjectManagement.model.Vendor;
import haagahelia.fi.ProjectManagement.model.VendorProject;
import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.model.project.ProjectStatus;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import haagahelia.fi.ProjectManagement.repository.VendorProjectRepository;
import haagahelia.fi.ProjectManagement.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class ProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner Demo(EmployeeRepository eRepository, ProjectRepository pRepository,
			ProjectExpenditureRepository peRepository, VendorRepository vRepository,
			VendorProjectRepository vpRepository) {

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

			log.info("create projects");
			// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Project p1 = new Project("CRM Implementation", LocalDate.parse("2019-03-20"), LocalDate.parse("2019-09-15"),
					ProjectStatus.COMPLETE, e5, 500000);
			Project p2 = new Project("Data Migration", LocalDate.parse("2020-10-10"), LocalDate.parse("2020-12-31"),
					ProjectStatus.PROCEEDING, e3, 300000);
			Project p3 = new Project("Q4 profit analysis", LocalDate.parse("2021-01-01"), LocalDate.parse("2021-02-20"),
					ProjectStatus.WAITING, e1, 150000);
			Project p4 = new Project("Data Security Policy Update", LocalDate.parse("2021-02-10"),
					LocalDate.parse("2021-03-31"), ProjectStatus.WAITING, e3, 2000);

			pRepository.save(p1);
			pRepository.save(p2);
			pRepository.save(p3);
			pRepository.save(p4);

			log.info("create project_expenditure");
			ProjectExpenditure pe1 = new ProjectExpenditure(p1, 750, "Vendor Contract");
			peRepository.save(pe1);

			log.info("create vendors");
			Vendor v1 = new Vendor("AAAVendor", "a@vendor.com", "1111");
			Vendor v2 = new Vendor("BBBVendor", "b@vendor.com", "2222");

			vRepository.save(v1);
			vRepository.save(v2);

			log.info("create vendor_project");
			VendorProject vp1 = new VendorProject(v1, p1, 750);
			vpRepository.save(vp1);
		};
	}

}
