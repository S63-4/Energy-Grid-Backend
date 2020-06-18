package com.energygrid.status_service.consumers;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MessageProducer {

    @Autowired
    @Qualifier("user-service-exchange")
    DirectExchange directExchange;

    @Autowired
    private RabbitTemplate template;

    public String sendGetZipCodeMessage(String emailAddress) {
        return (String) template.convertSendAndReceive(directExchange.getName(), emailAddress, "rpc-get-zipcode");
    }
}
