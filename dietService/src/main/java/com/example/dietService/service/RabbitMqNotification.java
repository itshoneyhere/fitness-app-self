package com.example.dietService.service;

import com.example.dietService.config.RabbitMqConfig;
import com.example.dietService.dto.event.NotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqNotification {

    private final RabbitTemplate rabbitTemplate;

    public void sendNotification(NotificationEvent event)
    {
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,
                RabbitMqConfig.ROUTING_KEY,
                event);
    }


}
