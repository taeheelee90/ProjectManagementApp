package taehee.lee.ProjectManagementApp_v2.domain.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.RequiredArgsConstructor;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.AppUser;
import taehee.lee.ProjectManagementApp_v2.domain.form.AppUsernameForm;
import taehee.lee.ProjectManagementApp_v2.repository.AppUserRepository;

@Component
@RequiredArgsConstructor
public class AppUsernameFormValidator implements Validator {

	private final AppUserRepository appUserRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return AppUsernameForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AppUsernameForm appUsernameForm = (AppUsernameForm) target;
		
		AppUser byUsername = appUserRepository.findByUsername(appUsernameForm.getUsername());
		
		if(byUsername != null) {
			errors.rejectValue("username", "wrong.value", "Username is already in use.");
		}
		
	}

}
