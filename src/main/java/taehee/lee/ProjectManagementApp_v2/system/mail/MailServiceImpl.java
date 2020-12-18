package taehee.lee.ProjectManagementApp_v2.system.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
	
	private final JavaMailSender javaMailSender;
	
	
	@Override
	public void send(MailMessage mailMsg) {
		
		MimeMessage mimeMsg = javaMailSender.createMimeMessage();
		
		
		try {
			MimeMessageHelper mimeMsgHelper = new MimeMessageHelper (mimeMsg, false, "UTF-8");
			mimeMsgHelper.setTo(mailMsg.getAddress());
			mimeMsgHelper.setSubject(mailMsg.getSubject());
			mimeMsgHelper.setText(mailMsg.getMessage(), true);
			javaMailSender.send(mimeMsg);
			
			log.info("Sent Email to : "  + mailMsg.getAddress());
			
		} catch (MessagingException e) {
			log.error("Failed to send email. " , e);
			throw new RuntimeException(e);
		}
		
		
	}

}
