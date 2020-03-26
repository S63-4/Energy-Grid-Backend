package com.energygrid.user_service.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendRegistrationMail(String targetAddress, String customerCode) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mailHelper = new MimeMessageHelper(message);

        try{
            mailHelper.setTo(targetAddress);
            mailHelper.setSubject("Registratie Energy Maatschappij");
            mailHelper.setText("Gebruik uw klantennummer om uw registratie af te ronden: " + customerCode);

            mailHelper.setFrom("test@example.com");

            javaMailSender.send(message);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
