package haagahelia.fi.ProjectManagement.contoller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import haagahelia.fi.ProjectManagement.entity.Title;
import haagahelia.fi.ProjectManagement.model.SignupForm;
import haagahelia.fi.ProjectManagement.model.User;
import haagahelia.fi.ProjectManagement.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository uRepository;
	
	
	
	@RequestMapping(value = "/signup")
	public String addUser(Title title, Model model) {
		model.addAttribute("signupform", new SignupForm());
		model.addAttribute("titles", title);
		return "main/signup";
	}
	
	@PostMapping(value = "/saveuser")
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // pw = pw check
				String pw = signupForm.getPassword();
				BCryptPasswordEncoder bcpw = new BCryptPasswordEncoder();
				String hashedpw = bcpw.encode(pw);

				User newUser = new User();
				newUser.setPasswordHash(hashedpw);
				newUser.setUsername(signupForm.getUsername());
				newUser.setTitle(signupForm.getTitle());
				newUser.setFirstName(signupForm.getFirstName());
				newUser.setLastName(signupForm.getLastName());

				if (uRepository.findByUsername(signupForm.getUsername()) == null) {  // usrename does not exist
					uRepository.save(newUser);
					
					// firstname + lastname Search from employee table?? -> NOT EXIST -> ROLE: USER
					// EXSISTS, Depending on Title (Manager and above : ADMIN) (Below Manager: USER)
				}

				else {  // username duplicated
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "main/signup";
				}
			} else { // pw != pw check
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "main/signup";
			}
		} else { // bindingResult.haserrors == true
			return "main/signup";
		}
		
		
		return "redirect:/login";
	}
	
	
}
