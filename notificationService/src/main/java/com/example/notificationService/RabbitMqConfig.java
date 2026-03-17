package com.example.notificationService;

import com.example.notificationService.dto.event.NotificationEvent;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String EXCHANGE = "fitness.exchange";
    public static final String ROUTING_KEY = "notification.email";
    public static final String QUEUE = "notification.queue";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @RabbitListener(queues = QUEUE)
    public void receive(NotificationEvent event) {
        System.out.println(event.userId());
        System.out.println(event.message());
    }


}
