package haagahelia.fi.ProjectManagement.contoller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import haagahelia.fi.ProjectManagement.model.project.ProjectExpenditure;
import haagahelia.fi.ProjectManagement.repository.ProjectExpenditureRepository;
import haagahelia.fi.ProjectManagement.service.ProjectExpenditureService;
import haagahelia.fi.ProjectManagement.system.NotEnoughBudgetException;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProjectExpenditureController {

	private final ProjectExpenditureRepository peRepository;
	private final ProjectExpenditureService service;
	
	
	
	/*
	 * Expenditure Management User can submit several expenditures for one project.
	 */

	// Show All Expenditures
	@GetMapping(value = "expendituretlist/{id}")
	public String expenditureList(@PathVariable("id") Long projectId, Model model) {
		model.addAttribute("expenditures", peRepository.findByProjectId(projectId));
		model.addAttribute("projectId", projectId);

		return "expenditure/expenditurelist";
	}

	// Add Expenditure
	@GetMapping(value = "expenditureadd/{id}")
	public String addExpenditure(@PathVariable("id") Long projectId, Model model) {
		model.addAttribute("expenditure", new ProjectExpenditure());
		//model.addAttribute("docs", new ExpenditureDocs());
		return "expenditure/addexpenditure";
	}

	// Handling expenditure submission
	@PostMapping(value = "/expenditureadd/{id}")
	public String expendtirueSubmit(@PathVariable("id") Long projectId, ProjectExpenditure expenditure) {

		service.addExpenditure(projectId, expenditure.getCost(), expenditure.getDescription());

		return "redirect:/expendituretlist/{id}";
	}
	
	/* 
	* Exception Controller 
	* Source: https://www.netjstech.com/2018/09/spring-mvc-exception-handling-example-exceptionhandler-controlleradvice.html
	*/
	
	@ExceptionHandler(NotEnoughBudgetException.class)
	  public ModelAndView handleIOException(HttpServletRequest request, Exception exception){
	    ModelAndView mv = new ModelAndView();
	    mv.addObject("exception", exception.getMessage());
	    mv.setViewName("error");
	    return mv;
	  }

	
}
