package com.energygrid.user_service.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    public EmailService emailService;

    @Test
    void sendRegistrationMail() {

        String email = "testmail";
        String customerCode = "27302732";

        emailService.sendRegistrationMail(email, customerCode);
    }
}