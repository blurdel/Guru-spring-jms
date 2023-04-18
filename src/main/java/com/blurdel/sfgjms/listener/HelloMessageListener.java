package com.blurdel.sfgjms.listener;

import com.blurdel.sfgjms.config.JmsConfig;
import com.blurdel.sfgjms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class HelloMessageListener {

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers,
                       Message message) {

        // Note: Only @Payload above is required, headers and message added for debugging

        System.out.println("RX message");
        System.out.println(helloWorldMessage);

//        throw new RuntimeException("doh!");  // Debug - look at message headers redelivered/deliveryCount
    }

}
