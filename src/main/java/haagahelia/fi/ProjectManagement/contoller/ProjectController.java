package haagahelia.fi.ProjectManagement.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import haagahelia.fi.ProjectManagement.repository.ProjectRepository;

@Controller
public class ProjectController {

	@Autowired
	private ProjectRepository pRepository;
	
	@RequestMapping(value="/")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value ="/list")
	public String projectList(Model model) {
		model.addAttribute("projects", pRepository.findAll());
		return "project/projectlist";
		
	}
	
}
