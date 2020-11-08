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
import haagahelia.fi.ProjectManagement.service.ProjectService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectRepository pRepository;
	private final EmployeeRepository eRepository;
	private final ProjectService service;

	// Main
	@GetMapping(value = "/")
	public String main() {
		return "main/home";
	}

	// Read All Projects
	@GetMapping(value = "/projectlist")
	public String projectList(Model model) {
		model.addAttribute("projects", pRepository.findAll());
		return "project/projectlist";

	}

	// Read Project Details
	@GetMapping(value = "/project/{id}")
	public String projectDetails(@PathVariable("id") Long projectId, Model model) {
		pRepository.findById(projectId).ifPresent(project -> model.addAttribute("project", project));
		pRepository.findById(projectId)
				.ifPresent(project -> model.addAttribute("projectManager", project.getProjectManager()));
		/*
		 * pRepository.findById(projectId) .ifPresent(project ->
		 * model.addAttribute("projectExpenditures", project.getProjectExpenditures()));
		 */
		return "project/projectdetails";
	}

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

	// Search Project by name
	@GetMapping("/project")
	public String searchProjectByName(Project project, BindingResult result, Map<String, Object> model) {

		// request without parameter returns all list
		if (project.getName() == null) {
			project.setName("");
		}

		// find project by project name
		Collection<Project> results = pRepository.findByName(project.getName());
		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("name", "notFound", "not found");
			return "project/projectlist";
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
			if (projectForm.getName() != null) { // Check null in all input fields
				if (projectForm.getStartDate() != null) {
					if (projectForm.getEndDate() != null) {
						if (projectForm.getEndDate().isAfter(projectForm.getStartDate())) { // Check end date validity

							if (projectForm.getBudget() != 0) {
								Project project = new Project(); // Save new project when all inputs pass validity
								project.setName(projectForm.getName());
								project.setStartDate(projectForm.getStartDate());
								project.setEndDate(projectForm.getEndDate());
								project.setStatus(createProjectStatus(projectForm));
								project.setProjectManager(projectForm.getProjectManager());
								project.setBudget(projectForm.getBudget());

								pRepository.save(project);

							} else { // budget is 0
								bindingResult.rejectValue("budget", "err.budget", "Budget can not be 0");
								model.addAttribute("employees", eRepository.findAll());
								return "project/projectForm";

							}
						} else { // End Date is before Start Date
							bindingResult.rejectValue("endDate", "err.endDate", "End Date must be after Start Date");
							model.addAttribute("employees", eRepository.findAll());
							return "project/projectForm";
						}
					} else { // End Date is null
						bindingResult.rejectValue("endDate", "err.endDate", "Must select end date");
						model.addAttribute("employees", eRepository.findAll());
						return "project/projectForm";
					}
				} else { // Start Date is null
					bindingResult.rejectValue("startDate", "err.startDate", "Must select start date");
					model.addAttribute("employees", eRepository.findAll());
					return "project/projectForm";
				}
			} else { // Name is null
				bindingResult.rejectValue("name", "err.name", "Must enter Project Name");
				model.addAttribute("employees", eRepository.findAll());
				return "project/projectForm";
			}

		} else { // Any other errors
			return "project/projectForm";
		}

		// No error found
		return "redirect:projectlist";

	}

	// Submit Method: Project Status Setting
	private ProjectStatus createProjectStatus(ProjectForm projectForm) {
		ProjectStatus status = null;
		LocalDate today = LocalDate.now();

		// Get values from form
		LocalDate startDate = projectForm.getStartDate();
		LocalDate endDate = projectForm.getEndDate();

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

	// Update Project
	@GetMapping(value = "/projectedit/{id}")
	public String updateProject(@PathVariable("id") Long projectId, Model model) {
		model.addAttribute("project", pRepository.findById(projectId));
		model.addAttribute("employees", eRepository.findAll());
		return "project/updateproject";
	}

	// Save updates
	@PostMapping(value = "/projectedit")
	public String updateHandling(Project project, Model model) {
		pRepository.save(project);
		return "redirect:projectlist";
	}

	// Handling Date
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	}

}
