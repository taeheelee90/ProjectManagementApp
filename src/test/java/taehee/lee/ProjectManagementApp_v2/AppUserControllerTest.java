package taehee.lee.ProjectManagementApp_v2;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import taehee.lee.ProjectManagementApp_v2.domain.appUser.AppUser;
import taehee.lee.ProjectManagementApp_v2.repository.AppUserRepository;
import taehee.lee.ProjectManagementApp_v2.system.mail.MailMessage;
import taehee.lee.ProjectManagementApp_v2.system.mail.MailService;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class AppUserControllerTest {

	@Autowired private MockMvc mockMvc;
	@Autowired private AppUserRepository appUserRepository;
	@MockBean MailService mailService;
	
	private AppUser validUser = AppUser.builder().username("test").email("test@email.com").password("12345").build();
	
	@AfterEach
	void afterEach() {
		appUserRepository.deleteAll();
	}
	
	@DisplayName("Sign Up View")
	@Test
	void signUpForm() throws Exception {
		mockMvc.perform(get("/signup"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("appuser/signup"))
			   .andExpect(model().attributeExists("signUpForm"));
	}
	
	@DisplayName("Sign Up Submission - Fail")
	@Test
	void signUp_submission_fail() throws Exception {
		mockMvc.perform(post("/signup")
				.param("username", "test1")
				.param("email", "wrong")   // invalid email format
				.param("password", "123")  // min: 5
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("appuser/signup"))
				.andExpect(unauthenticated());		
	}
	
	@DisplayName ("Sign Up Submission - Success")
	@Test
	void signUp_submission_success() throws Exception{
		
		mockMvc.perform(post("/signup")
				.param("username", validUser.getUsername())
				.param("email", validUser.getEmail())
				.param("password", validUser.getPassword())
				.with(csrf()))
				.andExpect(status().is3xxRedirection())  // 302
				.andExpect(view().name("redirect:/"))
				.andExpect(authenticated().withUsername(validUser.getUsername()));
		
		
		AppUser appUser = appUserRepository.findByEmail(validUser.getEmail());
		assertNotNull(appUser); // User saved
		assertNotEquals(appUser.getPassword(), "12345"); // PW encoded
		assertNotNull(appUser.getEmailCheckToken()); // Token generated
		
		then(mailService).should().send(any(MailMessage.class));
	}
	
	
	@DisplayName("Request for checking up verification email")
	@Test
	void check_email() throws Exception {
		validUser.setEmailVerified(false);
		appUserRepository.save(validUser);
		
		mockMvc.perform(get("/check-email")
				.param("email", validUser.getEmail()))
			    .andExpect(status().is3xxRedirection()); // 302
	}
	
	
	@DisplayName("Profile View")
	@Test
	void check_showProfile() throws Exception {
		appUserRepository.save(validUser);		
		String username = validUser.getUsername();
		
		mockMvc.perform(get("/profile/" + username))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("isOwner"))
				.andExpect(view().name("appuser/profile"));
	}
	
	@DisplayName("Email Login View")
	@Test
	void emailLoginForm() throws Exception {
		mockMvc.perform(get("/email-login"))
		 		.andExpect(status().isOk())
		 		.andExpect(view().name("appuser/email-login"));
			
	}
	
	
	
	

}
