package taehee.lee.ProjectManagementApp_v2.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.AppUser;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.CurrentUser;
import taehee.lee.ProjectManagementApp_v2.domain.form.SignUpForm;
import taehee.lee.ProjectManagementApp_v2.domain.validator.SignUpFormValidator;
import taehee.lee.ProjectManagementApp_v2.repository.AppUserRepository;
import taehee.lee.ProjectManagementApp_v2.service.AppUserService;

@Controller
@RequiredArgsConstructor
public class AppUserController {

	private final SignUpFormValidator signUpFormValidator;
	private final AppUserService appUserService;
	private final AppUserRepository appUserRepository;

	@InitBinder("signUpForm")
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(signUpFormValidator);

	}

	// Sign up
	@GetMapping(value = "/signup")
	public String signUpform(Model model) {
		model.addAttribute("signUpForm", new SignUpForm());
		return "appuser/signup";
	}

	// Handling sign up
	@PostMapping(value = "/signup")
	public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors) {

		if (errors.hasErrors()) {
			return "appuser/signup";
		}

		AppUser appUser = appUserService.processNewAccount(signUpForm);
		appUserService.login(appUser);

		return "redirect:/";
	}

	// Send email verification token
	@GetMapping("/check-email-token")
	public String checkEmailToken(String token, String email, Model model) {
		AppUser appUser = appUserRepository.findByEmail(email);
		String view = "appuser/email-checked";

		if (appUser == null) {
			model.addAttribute("error", "wrong.email");
			return view;
		}

		if (!appUser.isValidtoken(token)) {
			model.addAttribute("error", "wrong.email");
			return view;
		}

		appUserService.completeSignUp(appUser);
		model.addAttribute("username", appUser.getUsername());

		return view;
	}

	// Request for confirming verification email
	@GetMapping("/check-email")
	public String checkEmail(@CurrentUser AppUser appUser, Model model) {
		model.addAttribute("email", appUser.getEmail());
		return "appuser/email-check";
	}

	// Re-send verification email
	@GetMapping("/resend-confirm-email")
	public String resendConfirmEmail(@CurrentUser AppUser appUser, Model model) {
		if (!appUser.canResendEmail()) {
			model.addAttribute("error", "Confirmation email can be sent once in an hour.");
			model.addAttribute("email", appUser.getEmail());
			return "appuser/email-check";
		}

		appUserService.sendSignUpConfirmEmail(appUser);
		return "redirect:/";
	}

	@GetMapping("/profile/{username}")
	public String showProfile(@PathVariable String username, Model model, @CurrentUser AppUser appUser) {
		AppUser byUsername = appUserRepository.findByUsername(username);

		if (username == null) {
			throw new IllegalArgumentException("Can not find " + username);
		}

		model.addAttribute(byUsername);
		model.addAttribute("isOwner", byUsername.equals(appUser));

		return "appuser/profile";
	}

	@GetMapping("/email-login")
	public String emailLoginForm() {
		return "appuser/email-login";
	}

	@PostMapping("/email-login")
	public String emailLoginLink(String email, Model model, RedirectAttributes attributes) {
		AppUser appUser = appUserRepository.findByEmail(email);

		if (appUser == null) {
			model.addAttribute("error", "Email address is not registered.");
			return "appuser/email-login";
		}
		
		/*if(!appUser.canResendEmail()) {
			model.addAttribute("error", "Can not send email now. Please try again one hour later.");
			return "appuser/email-login";
		}
		*/
		appUserService.sendLoginLink(appUser);
		attributes.addFlashAttribute("message", "Sent login link to email.");
		return "redirect:/email-login";
	}
	
	
	@GetMapping("/login-by-email")
	public String loginByEmail (String token, String email, Model model) {
		AppUser appUser = appUserRepository.findByEmail(email);
		
		if(appUser == null || !appUser.isValidtoken(token)) {
			model.addAttribute("error", "Can not login");
			return "appuser/logged-in-by-email";
		}
		
		appUserService.login(appUser);
		return "appuser/logged-in-by-email";
	}
}
