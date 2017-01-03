package net.tralfamadore.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class: TestMailService
 * Created by billreh on 1/3/17.
 */
@RunWith(PowerMockRunner.class)
public class TestMailService {
    @Mock
    private JavaMailSender mailSender;
    private String from;
    private String subject;
    private String message;
    private String to;

    @Test
    public void testMailService() throws MessagingException {
        JavaMailSender ms = new JavaMailSenderImpl();
        MailService mailService = new MailService(mailSender);
        MimeMessage mimeMessage = ms.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        from = "bparker@matrix.gs";
        subject = "Hello";
        message = "Hi There";
        to = "billreh@gmail.com";
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        mailService.sendMail(to, from, subject, message);
        verify(mailSender).send(mimeMessage);
    }
}
