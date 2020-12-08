package taehee.lee.ProjectManagementApp_v2.domain.appUser;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class UserAccount extends User{

	private AppUser appUser;
	
	public UserAccount(AppUser appUser) {
		super(appUser.getUsername(), appUser.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
		this.appUser = appUser;
	}
	
}