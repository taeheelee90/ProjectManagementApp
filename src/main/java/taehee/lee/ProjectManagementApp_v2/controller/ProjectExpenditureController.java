package taehee.lee.ProjectManagementApp_v2.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.AppUser;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.CurrentUser;
import taehee.lee.ProjectManagementApp_v2.domain.project.ProjectExpenditure;
import taehee.lee.ProjectManagementApp_v2.repository.ProjectExpenditureRepository;
import taehee.lee.ProjectManagementApp_v2.service.ProjectExpenditureService;
import taehee.lee.ProjectManagementApp_v2.system.NotEnoughBudgetException;

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
	public String expenditureList(@CurrentUser AppUser appUser, @PathVariable("id") Long projectId, Model model) {
		model.addAttribute("expenditures", peRepository.findByProjectId(projectId));
		model.addAttribute("projectId", projectId);
		model.addAttribute(appUser);
		return "expenditure/expenditurelist";
	}

	// Add Expenditure
	@GetMapping(value = "expenditureadd/{id}")
	public String addExpenditure(@CurrentUser AppUser appUser, @PathVariable("id") Long projectId, Model model) {
		model.addAttribute("expenditure", new ProjectExpenditure());
		model.addAttribute(appUser);
		return "expenditure/addexpenditure";
	}

	// Handling expenditure submission
	@PostMapping(value = "/expenditureadd/{id}")
	public String expendtirueSubmit(@CurrentUser AppUser appUser, @PathVariable("id") Long projectId, ProjectExpenditure expenditure, Model model) {

		service.addExpenditure(projectId, expenditure.getCost(), expenditure.getDescription());
		model.addAttribute(appUser);
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

