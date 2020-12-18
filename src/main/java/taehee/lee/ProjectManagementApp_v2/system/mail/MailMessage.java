package taehee.lee.ProjectManagementApp_v2.system.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailMessage {

	private String address;
	
	private String subject;
	
	private String message;
}
