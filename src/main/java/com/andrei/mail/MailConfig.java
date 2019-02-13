package com.andrei.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Setter
@Getter
@Configuration
public class MailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String mailSenderUsername;

    @Value("${spring.mail.password}")
    private String mailSenderPassword;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String smtpTlsEnable;

    @Value("${spring.mail.properties.mail.smtp.starttls.required}")
    private String smtpTlsRequired;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String smtpAuth;

    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private String smtpConnectionTimeout;

    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private String smtpTimeout;

    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
    private String smtpWriteTimeout;

    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String transportProtocol;

    @Bean
    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(getHost());
        mailSender.setPort(getPort());

        mailSender.setUsername(getMailSenderUsername());
        mailSender.setPassword(getMailSenderPassword());

        Properties props = mailSender.getJavaMailProperties();

        props.put("mail.smtp.ssl.trust", getHost());
        props.put("mail.transport.protocol", getTransportProtocol());
        props.put("mail.smtp.auth", getSmtpAuth());
        props.put("mail.smtp.starttls.enable", getSmtpTlsEnable());
        props.put("mail.smtp.starttls.required", getSmtpTlsRequired());
        props.put("mail.smtp.connectiontimeout", getSmtpConnectionTimeout());
        props.put("mail.smtp.timeout", getSmtpTimeout());
        props.put("mail.smtp.writetimeout", getSmtpWriteTimeout());

        // development purposes
        props.put("mail.debug", "true");

        return mailSender;
    }
}