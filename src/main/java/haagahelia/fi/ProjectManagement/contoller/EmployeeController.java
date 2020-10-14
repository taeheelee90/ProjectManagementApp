package haagahelia.fi.ProjectManagement.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import haagahelia.fi.ProjectManagement.model.Employee;
import haagahelia.fi.ProjectManagement.repository.DepartmentRepository;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository eRepository;
	@Autowired
	private DepartmentRepository dRepository;
	
	// Read Employees
	@RequestMapping(value="/employeelist")
	public String employeeList(Model model) {
		model.addAttribute("employees", eRepository.findAll());
		return "employee/employeelist";
	}
	
	// Add Employee
	@GetMapping(value="/employeeadd")
	public String addEmployee(Model model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("departments", dRepository.findAll());
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
		model.addAttribute("departments", dRepository.findAll());
		return "employee/updateemployee";
	}
	
	//Delete Employee
	@GetMapping(value = "/employeedelete/{id}")
	public String deleteEmployee (@PathVariable("id") Long empId) {
		eRepository.deleteById(empId);
		return "redirect:../employeelist";
	}
}
