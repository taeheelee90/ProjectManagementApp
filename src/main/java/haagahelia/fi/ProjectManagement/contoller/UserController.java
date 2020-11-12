package haagahelia.fi.ProjectManagement.contoller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import haagahelia.fi.ProjectManagement.model.form.SignupForm;
import haagahelia.fi.ProjectManagement.model.user.User;
import haagahelia.fi.ProjectManagement.repository.UserRepository;

@Controller
public class UserController {

	@Autowired private UserRepository repository;

	// Login
	@GetMapping(value = "/login")
	public String login() {
		return "main/login";
	}

	// Sign up
	@GetMapping(value = "/signup")
	public String addUser(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "main/signup";
	}

	// Handling sign up
	@PostMapping(value = "/saveuser")
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match

				String pw = signupForm.getPassword();
				BCryptPasswordEncoder bcpw = new BCryptPasswordEncoder();
				String hashedpw = bcpw.encode(pw);

				User newUser = new User();
				newUser.setPasswordHash(hashedpw);
				newUser.setUsername(signupForm.getUsername());
				newUser.setRole("USER");
				
				if (repository.findByUsername(signupForm.getUsername()) == null) { // check user name duplication

					repository.save(newUser);

				} else { // User name duplication
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "main/signup";
				}

			} else { // Password does not match
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Password does not match");
				return "main/signup";
			}
		} else { // Any other errors
			return "main/signup";
		}
		return "redirect:/login";
	}
}