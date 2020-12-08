package taehee.lee.ProjectManagementApp_v2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import taehee.lee.ProjectManagementApp_v2.domain.appUser.AppUser;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.CurrentUser;



@Controller
public class MainController {
	
	//Main
	@GetMapping("/")
	public String home(@CurrentUser AppUser appUser, Model model) {
		if(appUser != null) {
			model.addAttribute(appUser);
		}
		
		return "index";
	}
	
	
	// Login
	@GetMapping(value = "/login")
	public String login() {
		return "appuser/login";
	}

}
