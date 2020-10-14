package haagahelia.fi.ProjectManagement.contoller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import haagahelia.fi.ProjectManagement.model.Project;
import haagahelia.fi.ProjectManagement.repository.EmployeeRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;

@Controller
public class ProjectController {

	@Autowired
	private ProjectRepository pRepository;
	@Autowired
	private EmployeeRepository eRepository;	

	// Main
	@RequestMapping(value="/")
	public String main() {
		return "main/home";
	}

	// Read Projects
	@RequestMapping(value = "/projectlist")
	public String projectList(Model model) {
		model.addAttribute("projects", pRepository.findAll());
		return "project/projectlist";

	}

	// Add Project
	@GetMapping(value = "/projectadd")
	public String addproject(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("employees", eRepository.findAll()); // leader만 보이게 해야지? level enum 추가하고 eRepository에 findByLevel 추가
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
		model.addAttribute("employees", eRepository.findAll()); // leader만 보이게 해야지? level enum 추가하고 eRepository에 findByLevel 추가
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
