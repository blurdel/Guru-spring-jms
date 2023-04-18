package com.blurdel.sfgjms.sender;

import com.blurdel.sfgjms.config.JmsConfig;
import com.blurdel.sfgjms.model.HelloWorldMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private  final ObjectMapper objectMapper;

    
    @Scheduled(fixedRate = 2000) // milliseconds
    public void SendMessage() {

//        System.out.println("Sending message");

        HelloWorldMessage msg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_HELLO_QUEUE, msg);

//        System.out.println("Message Sent");
    }

    @Scheduled(fixedRate = 2000) // milliseconds
    public void SendAndReceiveMessage() throws JMSException {

        HelloWorldMessage msg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello")
                .build();

        Message rxMessage = jmsTemplate.sendAndReceive(JmsConfig.TX_RX_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMsg = null;
                try {
                    helloMsg = session.createTextMessage(objectMapper.writeValueAsString(msg));
                    helloMsg.setStringProperty("_type", "com.blurdel.sfgjms.model.HelloWorldMessage");

                    System.out.println("Sending hello");

                    return helloMsg;
                }
                catch (JsonProcessingException e) {
                    throw new JMSException("boom!");
                }
            }
        });
        System.out.println(rxMessage.getBody(String.class));
    }

}
