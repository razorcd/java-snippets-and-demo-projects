package com.demo.spring.integration.channels.directchannel;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class AknowledgeService {

    @ServiceActivator(inputChannel = "channel2")
    public void acknowledge(Message<String> message) {
        message.getHeaders()
                .forEach((key, value) -> System.out.println(key + " : " + value));
        System.out.println(message.getPayload());
    }
}
