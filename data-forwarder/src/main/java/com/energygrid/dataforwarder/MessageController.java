package com.energygrid.dataforwarder;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Message("Hello " + HtmlUtils.htmlEscape(message.getName()));
    }

    @SendTo("/topic/regional")
    public Message regional(String message) throws InterruptedException {
        return new Message(message);
    }

    @SendTo("/topic/national")
    public Message national(String message) throws InterruptedException {
        return new Message(message);
    }

    @SendTo("/topic/market")
    public Message market(String message) throws InterruptedException {
        return new Message(message);
    }
}
