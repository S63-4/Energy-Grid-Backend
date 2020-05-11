package com.energygrid.status_service.consumers;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerMessagingConfig {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("data-processor-exchange");
    }

    private static class ConsumerConfig
    {
        @Bean
        public Queue saveStatusQueue () {
            return new AnonymousQueue();
    }

        @Bean
        public Binding saveStatusBinding (DirectExchange directExchange, Queue saveStatusQueue){
            return BindingBuilder.bind(saveStatusQueue).to(directExchange).with("saveStatus");
    }

        @Bean
        public MessageConsumer messageConsumer () {
            return new MessageConsumer();
        }
    }
}
