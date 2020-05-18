package com.energygrid.user_service.services;

import com.energygrid.user_service.mail.EmailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class EmailServiceTest {

    @Autowired
    public EmailService emailService;

    @Test
    public void sendRegistrationMail() {

//        boolean emailSend;
//
//        String email = "tcs.krijnen@student.fontys.nl";
//        String customerCode = "27302732";
//
//        emailSend = emailService.sendRegistrationMail(email, customerCode);
//
//        Assert.assertTrue("Email failed to send to " + email, emailSend);
    }
}