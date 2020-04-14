package com.energygrid.user_service.services;

import com.energygrid.user_service.mail.EmailService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    public EmailService emailService;

    @Test
    void sendRegistrationMail() {

        boolean emailSend;

        String email = "tcs.krijnen@student.fontys.nl";
        String customerCode = "27302732";

        emailSend = emailService.sendRegistrationMail(email, customerCode);

        Assert.assertTrue("Email failed to send to " + email, emailSend);
    }
}