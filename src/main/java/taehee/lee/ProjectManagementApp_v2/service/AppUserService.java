package taehee.lee.ProjectManagementApp_v2.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.AppUser;
import taehee.lee.ProjectManagementApp_v2.domain.appUser.UserAccount;
import taehee.lee.ProjectManagementApp_v2.domain.form.SignupForm;
import taehee.lee.ProjectManagementApp_v2.repository.AppUserRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
	
	private final AppUserRepository appUserRepository;
	private final JavaMailSender javaMailSender;
	private final PasswordEncoder passwordEncoder;
	


	public AppUser processNewAccount(@Valid SignupForm signupForm) {
		AppUser newUser = saveNewUser(signupForm);
		newUser.generateEmailCheckToken();
		sendSignUpConfirmEmail(newUser);
		
		return newUser;
	}

	private AppUser saveNewUser(@Valid SignupForm signupForm) {
		AppUser user = AppUser.builder()
									  .email(signupForm.getEmail())
									  .username(signupForm.getUsername())
									  .password(passwordEncoder.encode(signupForm.getPassword())).build();
		
		AppUser newUser = appUserRepository.save(user);
		return newUser;
	}
	

	public void sendSignUpConfirmEmail(AppUser newUser) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(newUser.getEmail());
		mailMessage.setSubject("[Verification] Welcome to Project Managment.");
		mailMessage.setText(
				"/check-email-token?token=" + newUser.getEmailCheckToken() + "&email=" + newUser.getEmail());
		
		javaMailSender.send(mailMessage);
		
	}


	public void login(AppUser appUser) {
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				new UserAccount (appUser),
				appUser.getPassword(),
				List.of(new SimpleGrantedAuthority("ROLE_USER")));
		
		SecurityContextHolder.getContext().setAuthentication(token);
		
	}
	
	
	@Transactional (readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
		AppUser appUser = appUserRepository.findByEmail(emailOrUsername);
		
		if(appUser == null) {
			appUser = appUserRepository.findByUsername(emailOrUsername);
		}
		
		if (appUser == null) {
			throw new UsernameNotFoundException (emailOrUsername);
		}
		
		return new UserAccount(appUser);		
		
	}

	public void completeSignUp(AppUser appUser) {
		appUser.completeSignUp();
		login(appUser);
		
	}

	
}
