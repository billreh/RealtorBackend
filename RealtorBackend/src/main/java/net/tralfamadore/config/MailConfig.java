package net.tralfamadore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * MailConfig - configuration for mail.
 * Created by Bill on 12/30/2016.
 */
@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("localhost");
        javaMailSender.setProtocol("smtp");
        javaMailSender.setPort(2525);
        javaMailSender.setUsername("test");
        javaMailSender.setPassword("test");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        javaMailSender.setJavaMailProperties(properties);
        return javaMailSender;
    }
}
