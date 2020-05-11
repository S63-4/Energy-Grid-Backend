package com.energygrid.status_service.consumers;
import com.energygrid.status_service.services.StatusService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageConsumer {

    @Autowired
    private StatusService statusService;

    public MessageConsumer() {
    }

    @RabbitListener(queues = "#{saveStatusQueue.name}")
    public void receive(String message) throws InterruptedException {
        Gson gson = new Gson();
        //TODO parse json to event class and pass in saveStatus()
        statusService.saveStatus();
    }
}
