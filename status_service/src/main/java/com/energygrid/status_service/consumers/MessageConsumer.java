package com.energygrid.status_service.consumers;
import com.energygrid.status_service.common.events.RegionalEvent;
import com.energygrid.status_service.common.utils.CustomJsonParser;
import com.energygrid.status_service.services.SavingService;
import com.energygrid.status_service.services.StatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageConsumer {

    @Autowired
    private SavingService savingService;

    @RabbitListener(queues = "#{saveStatusQueue.name}")
    public void receive(String message) {
        System.out.println("Message received: " + message.substring(0, 100) + "...");
        savingService.handleEvent(message);
    }
}
