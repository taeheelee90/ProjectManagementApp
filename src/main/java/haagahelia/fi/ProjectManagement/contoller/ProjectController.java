package haagahelia.fi.ProjectManagement.contoller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import haagahelia.fi.ProjectManagement.model.form.ProjectForm;
import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectStatus;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectRepository pRepository;
	private final EmployeeRepository eRepository;

	/*
	 * Main Page
	 */
	@GetMapping(value = "/")
	public String main() {
		return "main/home";
	}

	/*
	 * REST Service
	 */

	// REST: Read All Projects
	@RequestMapping(value = "/projects")
	public @ResponseBody List<Project> projectsRest() {
		return (List<Project>) pRepository.findAll();
	}

	// REST: Search Project by ID
	@GetMapping(value = "/projects/{id}")
	public @ResponseBody Optional<Project> findProjectRest(@PathVariable("id") Long projectId) {
		return pRepository.findById(projectId);
	}

	/*
	 * MVC
	 */
	// Read All Projects : Default order (Order by Id)
	@GetMapping(value = "/projectlist")
	public String projectList(Model model) {
		model.addAttribute("projects", pRepository.findByOrderById());
		return "project/projectlist";

	}

	// Change order (by name)
	@GetMapping(value = "/projectsbyname")
	public String projectsByName(Model model) {
		model.addAttribute("projects", pRepository.findByOrderByName());
		return "project/projectlist";

	}

	// Change order (by start date)
	@GetMapping(value = "projectstbystartdate")
	public String projectsByStartDate(Model model) {
		model.addAttribute("projects", pRepository.findByOrderByStartDate());
		return "project/projectlist";
	}

	// Change order (by end date)
	@GetMapping(value = "projectstbyenddate")
	public String projecsByEndDate(Model model) {
		model.addAttribute("projects", pRepository.findByOrderByEndDate());
		return "project/projectlist";
	}

	// Change order (by status)
	@GetMapping(value = "projectstbystatus")
	public String projecsByStatus(Model model) {
		model.addAttribute("projects", pRepository.findByOrderByStatus());
		return "project/projectlist";
	}

	// Read Project Details
	@GetMapping(value = "/project/{id}")
	public String projectDetails(@PathVariable("id") Long projectId, Model model) {
		pRepository.findById(projectId).ifPresent(project -> model.addAttribute("project", project));
		pRepository.findById(projectId)
				.ifPresent(project -> model.addAttribute("projectManager", project.getProjectManager()));

		return "project/projectdetails";
	}

	// Search Project by keyword in name
	@GetMapping("/project")
	public String searchProjectByName(Project project, BindingResult result, Map<String, Object> model) {

		// request without parameter returns all list
		if (project.getName() == null) {
			project.setName("");
		}

		// find project by project name
		Collection<Project> results = pRepository.findByName(project.getName());
		if (results.isEmpty()) {
			// no projects found
			result.rejectValue("name", "not Found", "not found");
			return "redirect:/projectlist";
		} else if (results.size() == 1) {
			// 1 project found
			project = results.iterator().next();
			return "redirect:/project/" + project.getId();
		} else {
			// multiple projects found
			model.put("projects", results);

			return "project/projectlist";
		}
	}

	// Add new Project
	@GetMapping(value = "/projectadd")
	public String addProject(Model model) {
		model.addAttribute("projectForm", new ProjectForm());
		model.addAttribute("employees", eRepository.findAll());
		return "project/projectForm";
	}

	// Submit Project
	@PostMapping(value = "/projectsubmit")
	public String saveProject(@Valid @ModelAttribute("projectForm") ProjectForm projectForm,
			BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {

			if (projectForm.getEndDate().isAfter(projectForm.getStartDate())) { // Check end date validity

				if (projectForm.getBudget() != 0) {

					// Save new project when all inputs pass validity
					Project project = new Project();
					project.setName(projectForm.getName());
					project.setStartDate(projectForm.getStartDate());
					project.setEndDate(projectForm.getEndDate());
					project.setStatus(createProjectStatus(projectForm.getStartDate(), projectForm.getEndDate()));
					project.setProjectManager(projectForm.getProjectManager());
					project.setBudget(projectForm.getBudget());

					pRepository.save(project);

				} else {
					// budget is 0
					bindingResult.rejectValue("budget", "err.budget", "Budget can not be 0");
					model.addAttribute("employees", eRepository.findAll());
					return "project/projectForm";

				}
			} else { // End Date is before Start Date
				bindingResult.rejectValue("endDate", "err.endDate", "End Date must be after Start Date");
				model.addAttribute("employees", eRepository.findAll());
				return "project/projectForm";
			}
		} else { // Any other errors
			model.addAttribute("employees", eRepository.findAll());
			return "project/projectForm";
		}

		// No error found
		return "redirect:projectlist";

	}

	// Update Project
	@GetMapping(value = "/projectedit/{id}")
	public String updateProject(@PathVariable("id") Long projectId, Model model) {
		model.addAttribute("project", pRepository.findById(projectId));
		model.addAttribute("employees", eRepository.findAll());
		return "project/updateproject";
	}

	// Submit updates
	@PostMapping(value = "/projectedit")
	public String saveUpdate(Project project, BindingResult bindingResult, Model model) {

		if (!bindingResult.hasErrors()) {
			if (project.getEndDate().isAfter(project.getStartDate())) { // Check end date validity

				// Get Project Status based on start and end dates
				ProjectStatus status = createProjectStatus(project.getStartDate(), project.getEndDate());

				// Check budget is not 0 for WAITING and PROCEEDING projects
				if (project.getBudget() != 0
						&& (status.equals(ProjectStatus.WAITING) || status.equals(ProjectStatus.PROCEEDING))) {

					project.setStatus(status);
					pRepository.save(project);

				} else if (project.getBudget() == 0 && status.equals(ProjectStatus.COMPLETE)) {
					// budget can be 0 when ProjectStatus is COMPLETE
					project.setStatus(status);
					pRepository.save(project);
				} else {

					if (status.equals(ProjectStatus.COMPLETE)) {
						// budget is more than 0 for COMPLETE project
						bindingResult.rejectValue("budget", "err.budget",
								"Please check budget. (RULE: COMPLETE Project must have 0) ");
						model.addAttribute("employees", eRepository.findAll());
					} else {
						// budget is 0 (and status is not COMPLETE)
						bindingResult.rejectValue("budget", "err.budget",
								"Please check budget. (RULE: Not complete projet can not have 0) ");
						model.addAttribute("employees", eRepository.findAll());
					}

					return "project/updateproject";
				}

			} else { // End Date is before Start Date
				bindingResult.rejectValue("endDate", "err.endDate", "End Date must be after Start Date");
				model.addAttribute("employees", eRepository.findAll());
				return "project/updateproject";
			}
		} else { // Any other errors
			model.addAttribute("employees", eRepository.findAll());
			return "project/updateproject";
		}
		// No error found
		return "redirect:projectlist";
	}

	/*
	 * Methods
	 */

	// Submit Method: Project Status Setting
	private ProjectStatus createProjectStatus(LocalDate startDate, LocalDate endDate) {
		ProjectStatus status = null;
		LocalDate today = LocalDate.now();

		if (endDate.isBefore(today)) {
			status = ProjectStatus.COMPLETE;
		} else if (startDate.isAfter(today)) {
			status = ProjectStatus.WAITING;
		} else if (startDate.equals(today) || startDate.isBefore(today)) {
			if (endDate.isAfter(today)) {
				status = ProjectStatus.PROCEEDING;
			}
		}
		return status;

	}

	// Handling Date
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	}

}