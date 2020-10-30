package haagahelia.fi.ProjectManagement;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import haagahelia.fi.ProjectManagement.entity.Title;
import haagahelia.fi.ProjectManagement.model.Contact;
import haagahelia.fi.ProjectManagement.model.Department;
import haagahelia.fi.ProjectManagement.model.Employee;
import haagahelia.fi.ProjectManagement.model.Project;
import haagahelia.fi.ProjectManagement.model.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.model.ProjectStatus;
import haagahelia.fi.ProjectManagement.model.Vendor;
import haagahelia.fi.ProjectManagement.model.VendorProject;
import haagahelia.fi.ProjectManagement.repository.DepartmentRepository;
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
	public CommandLineRunner Demo(DepartmentRepository dRepository, EmployeeRepository eRepository,
			ProjectRepository pRepository, ProjectExpenditureRepository peRepository, 
			VendorRepository vRepository, VendorProjectRepository vpRepository) {
		
		return (args) -> {
			log.info("Create Department");
			Department d1 = new Department("Sales");
			Department d2 = new Department("Marketing");
			Department d3 = new Department("Finance");
			Department d4 = new Department("Accounting");
			Department d5 = new Department("IT");
			Department d6 = new Department("HR");

			dRepository.save(d1);
			dRepository.save(d2);
			dRepository.save(d3);
			dRepository.save(d4);
			dRepository.save(d5);
			dRepository.save(d6);

			log.info("create contact");
			Contact c1 = new Contact ("a@mail.com", "000" );
			
			Employee e1 = new Employee("Alexander", "Adkins", Title.MANAGER, d1, c1);
			Employee e2 = new Employee("Roan", "Jackson", Title.ASSOCIATE, d3, c1);
			Employee e3 = new Employee("Izabela", "Bostock", Title.INTERN, d5, c1);
			Employee e4 = new Employee("Helen", "Leary", Title.CLERK, d4, c1);
			Employee e5 = new Employee("Linda", "Douglas", Title.DIRECTOR, d2, c1);
			Employee e6 = new Employee("Rafael", "Ortega", Title.MANAGER, d4, c1);
			Employee e7 = new Employee("Henry", "Stevens", Title.ASSOCIATE, d2, c1);
			Employee e8 = new Employee("Sharon", "Jenkins", Title.MANAGER, d3, c1);
			Employee e9 = new Employee("George", "Franklin", Title.ASSOCIATE, d1, c1);
			Employee e10 = new Employee("Betty", "Davis", Title.MANAGER, d5, c1);
			Employee e11 = new Employee("Eduardo", "Rodriquez", Title.MANAGER, d6, c1);

			eRepository.save(e1);
			eRepository.save(e2);
			eRepository.save(e3);
			eRepository.save(e4);
			eRepository.save(e5);
			eRepository.save(e6);
			eRepository.save(e7);
			eRepository.save(e8);
			eRepository.save(e9);
			eRepository.save(e10);
			eRepository.save(e11);

			log.info("create projects");
			//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Project p1 = new Project("CRM Implementation", LocalDate.parse("2019-03-20"), LocalDate.parse("2019-09-15"),
					ProjectStatus.COMPLETE, e1, e1.getDepartment(), "Y", 500000);
			Project p2 = new Project("Data Migration", LocalDate.parse("2020-10-10"), LocalDate.parse("2020-12-31"),
					ProjectStatus.PROCEEDING, e10, e10.getDepartment(), "N", 300000);
			Project p3 = new Project("Q4 profit analysis", LocalDate.parse("2021-01-01"), LocalDate.parse("2021-02-20"),
					ProjectStatus.WAITING, e8, e8.getDepartment(), "Y", 150000);

			pRepository.save(p1);
			pRepository.save(p2);
			pRepository.save(p3);
			
			log.info("create project_expenditure");
			ProjectExpenditure pe1 = new ProjectExpenditure (p1, 750, "Vendor Contract");
			peRepository.save(pe1);
			
			log.info("create vendors");
			Vendor v1 = new Vendor("AAAVendor", c1);
			Vendor v2 = new Vendor("BBBVendor", c1);
			
			vRepository.save(v1);
			vRepository.save(v2);
			
			
			log.info("create vendor_project");
			VendorProject vp1 = new VendorProject(v1, p1, 750);
			vpRepository.save(vp1);
		};
	}

}
