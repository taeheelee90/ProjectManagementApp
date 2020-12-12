package taehee.lee.ProjectManagementApp_v2.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.AppUser;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.CurrentUser;
import taehee.lee.ProjectManagementApp_v2.domain.form.AppUserProfileForm;
import taehee.lee.ProjectManagementApp_v2.domain.form.AppUsernameForm;
import taehee.lee.ProjectManagementApp_v2.domain.form.PasswordForm;
import taehee.lee.ProjectManagementApp_v2.domain.validator.PasswordFormValidator;
import taehee.lee.ProjectManagementApp_v2.service.AppUserService;

@Controller
@RequiredArgsConstructor
public class SettingController {

	private final ModelMapper modelMapper;
	private final AppUserService appUserService;

	@InitBinder("passwordForm")
	public void passwordFormInitBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new PasswordFormValidator());
	}

	@GetMapping("/settings/profile")
	public String profileForm(@CurrentUser AppUser appUser, Model model) {
		model.addAttribute(appUser);
		model.addAttribute(modelMapper.map(appUser, AppUserProfileForm.class));
		return "settings/profile";
	}

	@PostMapping("/settings/profile")
	public String profileUpdate(@CurrentUser AppUser appUser, @Valid AppUserProfileForm profileForm, Errors errors,
			Model model, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			model.addAttribute(appUser);
			return "settings/profile";
		}

		appUserService.profileUpdate(appUser, profileForm);
		attributes.addFlashAttribute("message", "Successfully updated profile.");
		return "redirect:/settings/profile";

	}

	@GetMapping("/settings/password")
	public String passwordForm(@CurrentUser AppUser appUser, Model model) {
		model.addAttribute(appUser);
		model.addAttribute(new PasswordForm());
		return "settings/password";
	}

	@PostMapping("/settings/password")
	public String passwordUpdate(@CurrentUser AppUser appUser, @Valid PasswordForm passwordForm, Errors errors,
			Model model, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			model.addAttribute(appUser);
			return "settings/password";
		}

		appUserService.passwordUpdate(appUser, passwordForm.getNewPassword());
		attributes.addFlashAttribute("message", "Successfully updated password.");
		return "redirect:/settings/password";
	}

	@GetMapping("/settings/account")
	public String usernameForm(@CurrentUser AppUser appUser, Model model) {
		model.addAttribute(appUser);
		model.addAttribute(modelMapper.map(appUser, AppUsernameForm.class));
		return "settings/account";
	}

	@PostMapping("/settings/account")
	public String usernameUpdate(@CurrentUser AppUser appUser, @Valid AppUsernameForm appUsernameForm, Errors errors,
			Model model, RedirectAttributes attributes) {

		if(errors.hasErrors()) {
			model.addAttribute(appUser);
			return "settings/account";
		}
		
		appUserService.updateUsername (appUser, appUsernameForm);
		attributes.addFlashAttribute("message", "Successfully updated username.");
		return "redirect:/settings/account";
	}
}
