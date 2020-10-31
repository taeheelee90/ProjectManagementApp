package haagahelia.fi.ProjectManagement.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import haagahelia.fi.ProjectManagement.model.Department;
import haagahelia.fi.ProjectManagement.model.Vendor;
import haagahelia.fi.ProjectManagement.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentRepository repository;

	// Read Department
	@RequestMapping(value = "/departmentlist")
	public String employeeList(Model model) {
		model.addAttribute("departments", repository.findAll());
		return "department/departmentlist";
	}

	// Add Department
	@GetMapping(value = "/departmentadd")
	public String addEmployee(Model model) {
		model.addAttribute("department", new Vendor());
		model.addAttribute("departments", repository.findAll());
		return "department/adddepartment";

	}

	// Submit Department
	@PostMapping(value = "/departmentsubmit")
	public String submitEmployee(Department department, Model model) {
		model.addAttribute("department", repository.save(department));
		return "redirect:departmentlist";
	}

	// Update Department
	@GetMapping(value = "/departmentedit/{id}")
	public String updateEmployee(@PathVariable("id") Long depId, Model model) {
		model.addAttribute("department", repository.findById(depId));
		return "department/updatedepartment";
	}

	// Delete Department
	@GetMapping(value = "/departmentdelete/{id}")
	public String deleteEmployee(@PathVariable("id") Long depId) {
		repository.deleteById(depId);
		return "redirect:../departmentlist";
	}
}
