package haagahelia.fi.ProjectManagement.contoller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import haagahelia.fi.ProjectManagement.model.Employee;
import haagahelia.fi.ProjectManagement.model.form.EmployeeForm;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeRepository eRepository;

	/*
	 * REST Service
	 */

	// REST : Read All Employees
	@GetMapping(value = "employees")
	public @ResponseBody List<Employee> employeesRest() {
		return (List<Employee>) eRepository.findAll();
	}

	// REST : Search Employees by ID
	@GetMapping(value = "employees/{id}")
	public @ResponseBody Optional<Employee> findEmployeeRest(@PathVariable("id") Long empId) {
		return eRepository.findById(empId);
	}

	/*
	 * MVC
	 */

	// Read All Employees
	@RequestMapping(value = "/employeelist")
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
	@GetMapping(value = "/employeeadd")
	public String addEmployee(Model model) {
		model.addAttribute("employeeForm", new EmployeeForm());
		return "employee/employeeform";

	}

	// Submit Employee
	@PostMapping(value = "/employeesubmit")
	public String saveEmployee(@Valid @ModelAttribute("employeeForm") EmployeeForm employeeForm,
			BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) { // check validity

			// Save new Employee when all inputs pass validity
			Employee employee = new Employee();
			employee.setFirstName(employeeForm.getFirstName());
			employee.setLastName(employeeForm.getLastName());
			employee.setEmail(employeeForm.getEmail());
			employee.setPhone(employeeForm.getPhone());
			employee.setDepartment(employeeForm.getDepartment());

			eRepository.save(employee);

		} else { // Any errors occurred
			model.addAttribute("employees", eRepository.findAll());
			return "employee/employeeform";
		}

		// No error found
		return "redirect:employeelist";

	}

	// Update Employee
	@GetMapping(value = "/employeeedit/{id}")
	public String updateEmployee(@PathVariable("id") Long empId, Model model) {
		model.addAttribute("employee", eRepository.findById(empId));
		return "employee/updateemployee";
	}

	// Save updates
	@PostMapping(value = "/employeeedit")
	public String updateHandling(Employee employee, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "employee/updateemployee";
		} else {
			eRepository.save(employee);
			return "redirect:employeelist";
		}
	}

	// Delete Employee
	@GetMapping(value = "/employeedelete/{id}")
	public String deleteEmployee(@PathVariable("id") Long empId) {
		eRepository.deleteById(empId);
		return "redirect:../employeelist";
	}
}
