package net.tralfamadore.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
	private transient JavaMailSender mailSender;

	@Inject
	public MailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String to, String from, String subject, String message) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
		messageHelper.setFrom(from);
		messageHelper.setTo(to);
		messageHelper.setSubject(subject);
		messageHelper.setText(message);
        mailSender.send(mimeMessage);
	}
}
