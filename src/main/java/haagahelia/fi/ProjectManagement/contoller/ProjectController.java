package haagahelia.fi.ProjectManagement.contoller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import haagahelia.fi.ProjectManagement.model.project.Project;
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
		/*pRepository.findById(projectId)
				.ifPresent(project -> model.addAttribute("projectExpenditures", project.getProjectExpenditures()));*/
		return "project/projectdetails";
	}

	
	// Search Project by name
	@GetMapping("/project")
	public String searchProjectByName (Project project, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (project.getName() == null) {
			project.setName(""); // empty string signifies broadest possible search
		}

		// find owners by name
		Collection<Project> results = pRepository.findByName(project.getName());
		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("name", "notFound", "not found");
			return "project/projectlist";
		}
		else if (results.size() == 1) {
			// 1 owner found
			project = results.iterator().next();
			return "redirect:/project/" + project.getId();
		}
		else {
			// multiple owners found
			model.put("projects", results);
			//return "project/projectSearch";
			return "project/projectlist";
		}
	}
	

	// Add Project
	@GetMapping(value = "/projectadd")
	public String addproject(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("employees", eRepository.findAll());
		return "project/addproject";
	}

	// Submit Project
	@PostMapping(value = "/projectsubmit")
	public String submitProject(Project project, Model model) {
		model.addAttribute("project", pRepository.save(project));
		return "redirect:projectlist";
	}

	// Update Project
	@GetMapping(value = "/projectedit/{id}")
	public String updateProject(@PathVariable("id") Long projectId, Model model) {
		model.addAttribute("project", pRepository.findById(projectId));
		model.addAttribute("employees", eRepository.findAll());
		return "project/updateproject";
	}

	/* Delete Project
	@GetMapping(value = "/projectdelete/{id}")
	public String deleteProject(@PathVariable("id") Long projectId) {
		service.cancelProject(projectId);
		return "main/home";
	}*/

	// Handling Date
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	}

}
