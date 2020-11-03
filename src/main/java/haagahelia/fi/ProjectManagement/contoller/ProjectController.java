package haagahelia.fi.ProjectManagement.contoller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

@Controller  
@RequiredArgsConstructor
public class ProjectController {

	private final ProjectRepository pRepository;
	private final EmployeeRepository eRepository;	
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
		pRepository.findById(projectId).ifPresent(project -> model.addAttribute("projectManager", project.getProjectManager()));
		
		return "project/projectdetails";
	}
	
	
	
	/**
	 * Custom handler for displaying an owner.
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 
	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Owner owner = this.owners.findById(ownerId);
		for (Pet pet : owner.getPets()) {
			pet.setVisitsInternal(visits.findByPetId(pet.getId()));
		}
		mav.addObject(owner);
		return mav;
	}*/
	
	

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

	// Delete Project
	@GetMapping(value = "/projectdelete/{id}")
	public String deleteProject(@PathVariable("id") Long projectId) {
		pRepository.deleteById(projectId);
		return "redirect:../projectlist";
	}

	// Handling Date
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	}

}
