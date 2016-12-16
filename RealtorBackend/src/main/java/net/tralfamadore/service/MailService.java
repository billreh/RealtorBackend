package net.tralfamadore.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	private transient JavaMailSender mailSender;

	@Autowired
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
