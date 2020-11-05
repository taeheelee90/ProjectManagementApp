package haagahelia.fi.ProjectManagement.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import haagahelia.fi.ProjectManagement.service.ProjectExpenditureService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectExpenditureController {

	private final ProjectRepository pRepository;
	private final ProjectExpenditureRepository peRepository;
	private final ProjectExpenditureService service;
	//private final ProjectService pService;
	
	@GetMapping(value ="expendituretlist/{id}")
	public String expenditureList(@PathVariable("id") Long projectId, Model model) {
		model.addAttribute("projectId", projectId);
		model.addAttribute("expenditures", peRepository.findByProjectId(projectId));
		return "expenditure/expenditurelist";
	}

	/*@GetMapping(value = "/expenditureform/{id}")
	public String expenditureForm(@PathVariable("id") Long projectId, Model model) {
		ProjectExpenditure projectExpenditure = new ProjectExpenditure();
		pRepository.findById(projectId).ifPresent(p -> projectExpenditure.setProject(p));
		projectExpenditure.createExpenditure(projectExpenditure.getProject(), projectExpenditure.getCost());
		model.addAttribute("expenditure", projectExpenditure);

		return "expenditure/addexpenditure";
	}*/
	
	// Add Expenditure
	@GetMapping(value = "expenditureadd/{id}")
	public String addExpenditure(@PathVariable("id") Long projectId, Model model) {
		
		ProjectExpenditure expenditure = new ProjectExpenditure();
		pRepository.findById(projectId).ifPresent(p -> expenditure.setProject(p));
		model.addAttribute("expenditure", expenditure);

		
		return "expenditure/addexpenditure";
	}

	@PostMapping(value = "/expenditureadd/{id}")
	public String expendtirueSubmit(@PathVariable("id") Long project_Id, @RequestParam("project.id") Long projectId, @RequestParam("cost") int cost, @RequestParam("description") String description, Model model) {
		
		System.out.println(projectId);
		System.out.println(cost);
		System.out.println(description);
		service.addExpenditure(projectId, cost, description);		
		
		//return "redirect:/project/{id}";
		return "redirect:/expendituretlist/{id}";
		
	}

}
