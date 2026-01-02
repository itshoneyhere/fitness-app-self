package com.example.dietService.service.rabbitmq;

import com.example.dietService.config.RabbitMqConfig;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMqConfig rabbitMqConfig;

    public void sendEmailNotification(Object payload)
    {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE,
                rabbitMqConfig.getROUTING_KEY(),
                payload
                );
    }
}
