package haagahelia.fi.ProjectManagement.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import haagahelia.fi.ProjectManagement.model.project.Project;
import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import haagahelia.fi.ProjectManagement.repository.ProjectRepository;
import haagahelia.fi.ProjectManagement.service.ProjectService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectExpenditureController {

	private final ProjectRepository pRepository;
	private final ProjectExpenditureRepository peRepository;
	//private final ProjectService pService;
	
	@GetMapping(value ="expendituretlist/{id}")
	public String expenditureList(@PathVariable("id") Long projectId, Model model) {
		
		return "expenditure/expenditurelist";
	}

	@GetMapping(value = "/expenditureform/{id}")
	public String expenditureForm(@PathVariable("id") Long projectId, Model model) {
		ProjectExpenditure projectExpenditure = new ProjectExpenditure();
		pRepository.findById(projectId).ifPresent(p -> projectExpenditure.setProject(p));

		model.addAttribute("expenditure", projectExpenditure);

		return "expenditure/addexpenditure";
	}

	@PostMapping(value = "/expendituresubmit")
	public String expendtirueSubmit(ProjectExpenditure expenditure, Model model) {
		
		pRepository.findById(expenditure.getProject().getId())
				.ifPresent(p -> expenditure.addExpenditure(expenditure.getProject(), expenditure.getCost()));
		peRepository.save(expenditure);
		
		return "redirect:projectlist";
		
		
	}

}
