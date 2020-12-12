package taehee.lee.ProjectManagementApp_v2.controller;

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

import lombok.RequiredArgsConstructor;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.AppUser;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.CurrentUser;
import taehee.lee.ProjectManagementApp_v2.domain.employee.Employee;
import taehee.lee.ProjectManagementApp_v2.domain.form.EmployeeForm;
import taehee.lee.ProjectManagementApp_v2.repository.EmployeeRepository;

@Controller
@RequiredArgsConstructor
public class ProjectManagerController {

	private final EmployeeRepository eRepository;
	private static final String emailRegex = "^(.+)@(.+)$";
	
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
	public String employeeList(@CurrentUser AppUser appUser, Model model) {
		model.addAttribute(appUser);
		model.addAttribute("employees", eRepository.findAll());
		return "employee/employeelist";
	}

	// Read Employee Details
	@GetMapping(value = "/employee/{id}")
	public String projectDetails(@CurrentUser AppUser appUser, @PathVariable("id") Long employeeId, Model model) {
		model.addAttribute(appUser);
		eRepository.findById(employeeId).ifPresent(employee -> model.addAttribute("employee", employee));
		eRepository.findById(employeeId).ifPresent(employee -> model.addAttribute("projects", employee.getProjects()));

		return "employee/employeedetails";
	}

	// Add Employee
	@GetMapping(value = "/employeeadd")
	public String addEmployee(@CurrentUser AppUser appUser, Model model) {
		model.addAttribute(appUser);
		model.addAttribute("employeeForm", new EmployeeForm());
		return "employee/employeeform";

	}

	// Submit Employee
	@PostMapping(value = "/employeesubmit")
	public String saveEmployee(@CurrentUser AppUser appUser, @Valid @ModelAttribute("employeeForm") EmployeeForm employeeForm, Model model,
			BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) { // check validity
			if (employeeForm.getEmail().matches(emailRegex)) { // Check email format
				// Save new Employee when all inputs pass validity
				Employee employee = new Employee();
				employee.setFirstName(employeeForm.getFirstName());
				employee.setLastName(employeeForm.getLastName());
				employee.setEmail(employeeForm.getEmail());
				employee.setPhone(employeeForm.getPhone());
				employee.setDepartment(employeeForm.getDepartment());

				eRepository.save(employee);
			} else { // Email form error
				model.addAttribute(appUser);
				bindingResult.rejectValue("email", "err.email", "Email format is invalid");
				return "employee/employeeform";
			}
		} else { // Any errors occurred
			model.addAttribute(appUser);
			model.addAttribute("employees", eRepository.findAll());
			return "employee/employeeform";
		}
		// No error found
		model.addAttribute(appUser);
		return "redirect:employeelist";

	}

	// Update Employee
	@GetMapping(value = "/employeeedit/{id}")
	public String updateEmployee(@CurrentUser AppUser appUser, @PathVariable("id") Long empId, Model model) {
		eRepository.findById(empId).ifPresent(employee -> model.addAttribute("employee", employee));
		model.addAttribute(appUser);

		return "employee/updateemployee";
	}

	// Save updates
	@PostMapping(value = "/employeeedit")
	public String updateHandling(@CurrentUser AppUser appUser, @Valid Employee employee, Model model, BindingResult bindingResult) {

		if (!bindingResult.hasErrors()) {
			if (employee.getEmail().matches(emailRegex)) {
				
				eRepository.save(employee);
				return "redirect:employeelist";
			} else { // Email form error
				model.addAttribute(appUser);
				bindingResult.rejectValue("email", "err.email", "Email format is invalid");
				return "employee/updateemployee";
			}
		} else {
			model.addAttribute(appUser);
			return "employee/updateemployee";
		}
	}

}

