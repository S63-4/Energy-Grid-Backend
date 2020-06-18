package com.energygrid.user_service.messaging;

import com.energygrid.user_service.services.CustomerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageConsumer {

    @Autowired
    CustomerService customerService;

    @RabbitListener
    public String receiveZipCodeRequest(String emailAddress) {
        return customerService.getZipCodeByEmail(emailAddress);
    }
}
