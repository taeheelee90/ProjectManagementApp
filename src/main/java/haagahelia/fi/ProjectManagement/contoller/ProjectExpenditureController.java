package haagahelia.fi.ProjectManagement.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;

@Controller  
@RequiredArgsConstructor
public class ProjectExpenditureController {
	
	private final ProjectRepository pRepository;
	private final ProjectExpenditureRepository peRepository;
	
	@GetMapping(value= "/expenditureform/{id}")
	public String expenditureForm (@PathVariable("id") Long projectId, Model model) {
		model.addAttribute("expenditure", new ProjectExpenditure());
		model.addAttribute("project", pRepository.findById(projectId));
		return "expenditure/addexpenditure";
	}
	
	@PostMapping(value="/expendituresubmit")
	public String expendtirueSubmit (ProjectExpenditure expenditure, Model model) {
		model.addAttribute(peRepository.save(expenditure));
		
		return "redirect:projectlist";
	}

}
