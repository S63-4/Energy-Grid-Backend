package com.energygrid.status_service.consumers;
import com.energygrid.status_service.services.SavingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageConsumer {

    @Autowired
    private SavingService savingService;

    @RabbitListener(queues = "#{regionalHourQueue.name}")
    public void receive(String message) {
        System.out.println("Message received: " + message.substring(0, 100) + "...");
        savingService.handleHourEvent(message);
    }
}
