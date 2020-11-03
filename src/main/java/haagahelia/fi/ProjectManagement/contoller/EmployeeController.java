package haagahelia.fi.ProjectManagement.contoller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import haagahelia.fi.ProjectManagement.model.Employee;
import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

	
	private final EmployeeRepository eRepository;

	
	// Read All Employees
	@RequestMapping(value="/employeelist")
	public String employeeList(Model model) {
		model.addAttribute("employees", eRepository.findAll());
		return "employee/employeelist";
	}
	
	
	// Read Employee Details
	@GetMapping(value = "/employee/{id}")
	public String projectDetails(@PathVariable("id") Long employeeId, Model model) {		 		
		eRepository.findById(employeeId).ifPresent(employee -> model.addAttribute("employee", employee));
		eRepository.findById(employeeId).ifPresent(employee -> model.addAttribute("projects", employee.getProjects()));
		
		return "employee/employeedetails";
	}

	
	// Add Employee
	@GetMapping(value="/employeeadd")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		return "employee/addemployee";
		
	}
	
	// Submit Employee
	@PostMapping(value="/employeesubmit")
	public String submitEmployee (Employee employee, Model model) {
		model.addAttribute("employee", eRepository.save(employee));
		return "redirect:employeelist";
	}
	
	//Update Employee
	@GetMapping(value="/employeeedit/{id}")
	public String updateEmployee (@PathVariable("id") Long empId, Model model) {
		model.addAttribute("employee", eRepository.findById(empId));
		return "employee/updateemployee";
	}
	
	//Delete Employee
	@GetMapping(value = "/employeedelete/{id}")
	public String deleteEmployee (@PathVariable("id") Long empId) {
		eRepository.deleteById(empId);
		return "redirect:../employeelist";
	}
}
