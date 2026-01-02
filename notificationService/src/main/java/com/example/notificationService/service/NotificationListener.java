package com.example.notificationService.service;

import com.example.notificationService.config.RabbitMqConfig;
import com.example.notificationService.dto.EmailEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {
    private final EmailService emailService;

    public NotificationListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE)
    public void handleEmailNotification(EmailEvent emailEvent)
    {
        System.out.println("notification received");
        emailService.sendSimpleEmail(emailEvent.to, emailEvent.subject, emailEvent.body);
    }

}
