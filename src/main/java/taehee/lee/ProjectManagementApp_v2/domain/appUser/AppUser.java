package taehee.lee.ProjectManagementApp_v2.domain.appUser;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Email;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import taehee.lee.ProjectManagementApp_v2.domain.BaseEntity;

@Entity
@Getter @Setter
@EqualsAndHashCode( of = "id")
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "AppUsers")
public class AppUser extends BaseEntity {

	@Email
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	private String description;
	
	private String phone;
	
	private boolean emailVerified;
	
	private String emailCheckToken;
	
	private LocalDateTime emailCheckTokenGeneratedAt;
	
	private LocalDateTime joinedAt;
	
	@Lob @Basic(fetch = FetchType.EAGER)
	private String profileImage;

	public void generateEmailCheckToken() {
		this.emailCheckToken = UUID.randomUUID().toString();
		this.emailCheckTokenGeneratedAt = LocalDateTime.now();
		
	}

	public boolean isValidtoken(String token) {
		return this.emailCheckToken.equals(token);
	}

	public void completeSignUp() {
		this.setEmailVerified(true);
		this.setJoinedAt(LocalDateTime.now());
		
	}
	
	/*
	 * Verification email can be sent only once in an hour. 
	 */

	public boolean canResendEmail() {
		return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
	}


	
}
