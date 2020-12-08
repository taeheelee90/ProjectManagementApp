package taehee.lee.ProjectManagementApp_v2.system;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.RequiredArgsConstructor;
import taehee.lee.ProjectManagementApp_v2.service.AppUserService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	private final AppUserService appUserService;
	private final DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.mvcMatchers("/", "/login", "/signup", "/check-email-token", "/email-login", "/check-email-login", "/login-link").permitAll()
			.mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
			.anyRequest().authenticated();
		
		http.formLogin()
			.loginPage("/login").permitAll();
		
		http.logout()
			.logoutSuccessUrl("/");
		
		http.rememberMe()
			.userDetailsService(appUserService)
			.tokenRepository(tokenRepository());
	}

	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		
		return jdbcTokenRepository;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		 	.mvcMatchers("/node_modules/**")
		 	.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
}