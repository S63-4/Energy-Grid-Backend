//package com.energygrid.dataforwarder.events;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class MessageConfig {
//
//    @Bean
//    public DirectExchange direct() {
//        return new DirectExchange("simulator");
//    }
//
//    private static class ConsumerConfig {
//
//        @Bean
//        public Queue autoDeleteQueue() {
//            return new AnonymousQueue();
//        }
//
//        @Bean
//        public Binding binding(DirectExchange directExchange, Queue autoDeleteQueue) {
//            return BindingBuilder.bind(autoDeleteQueue).to(directExchange).with("regional");
//        }
//
//        @Bean
//        public MessageConsumer consumer() {
//            return new MessageConsumer();
//        }
//    }
//}
