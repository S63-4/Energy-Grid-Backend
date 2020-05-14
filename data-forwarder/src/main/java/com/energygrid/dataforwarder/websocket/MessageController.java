package com.energygrid.dataforwarder.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate msgController;

    public void regional(String message) throws InterruptedException {
        msgController.convertAndSend("/topic/regional", message);
    }

    public void national(String message) throws InterruptedException {
        msgController.convertAndSend("/topic/national", message);
    }

    public void market(String message) throws InterruptedException {
        msgController.convertAndSend("/topic/market", message);
    }
}
