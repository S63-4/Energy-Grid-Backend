package com.energygrid.user_service.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String targetAddress, String customerCode) {

        var mail = new SimpleMailMessage();

        mail.setTo(targetAddress);
        mail.setSubject("Registratie Energy Maatschappij");
        mail.setText("Gebruik uw klantennummer om uw registratie af te ronden: " + customerCode);

        mail.setFrom("test@example.com");

        javaMailSender.send(mail);
    }
}
