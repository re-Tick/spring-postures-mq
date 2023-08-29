package com.example.springpostgresmq.scheduler;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQScheduler {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 5000)  // 5 seconds
    public void sendMessage() {
        rabbitTemplate.convertAndSend("myQueue", "Hello from the scheduled task!");
        System.out.println("Message sent to RabbitMQ!");
    }
}

