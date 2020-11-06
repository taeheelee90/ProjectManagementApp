package haagahelia.fi.ProjectManagement.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import haagahelia.fi.ProjectManagement.service.ProjectExpenditureService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectExpenditureController {

	private final ProjectExpenditureRepository peRepository;
	private final ProjectExpenditureService service;
	
	
	@GetMapping(value ="expendituretlist/{id}")
	public String expenditureList(@PathVariable("id") Long projectId, Model model) {
		model.addAttribute("projectId", projectId);
		model.addAttribute("expenditures", peRepository.findByProjectId(projectId));
		return "expenditure/expenditurelist";
	}

	// Add Expenditure
	@GetMapping(value = "expenditureadd/{id}")
	public String addExpenditure(@PathVariable("id") Long projectId, Model model) {		
		model.addAttribute("expenditure", new ProjectExpenditure());		
		return "expenditure/addexpenditure";
	}

	@PostMapping(value = "/expenditureadd/{id}")
	public String expendtirueSubmit(@PathVariable("id") Long projectId, ProjectExpenditure expenditure) {
		
		service.addExpenditure(projectId, expenditure.getCost(), expenditure.getDescription());		
		return "redirect:/expendituretlist/{id}";
		
	}
	
}
