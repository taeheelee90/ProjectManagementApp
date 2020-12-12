package taehee.lee.ProjectManagementApp_v2.domain.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.RequiredArgsConstructor;
import taehee.lee.ProjectManagementApp_v2.domain.form.SignUpForm;
import taehee.lee.ProjectManagementApp_v2.repository.AppUserRepository;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {

	private final AppUserRepository appUserRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(SignUpForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SignUpForm signUpForm = (SignUpForm) target;
		
		if (appUserRepository.existsByEmail(signUpForm.getEmail())) {
			errors.rejectValue("email", "invalid.email", new Object[] {signUpForm.getEmail()}, "Email is already in use.");
		}
		
		if (appUserRepository.existsByUsername(signUpForm.getUsername())) {
			errors.rejectValue("username", "invalid.username", new Object[] {signUpForm.getUsername()}, "Username is already in use.");
		}
	}
	
	
}
