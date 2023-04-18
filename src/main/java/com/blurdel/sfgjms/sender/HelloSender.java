package com.blurdel.sfgjms.sender;

import com.blurdel.sfgjms.config.JmsConfig;
import com.blurdel.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    
    @Scheduled(fixedRate = 2000) // 2 sec
    public void SendMessage() {

        System.out.println("Sending message");

        HelloWorldMessage msg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, msg);

        System.out.println("Message Sent");
    }
}
