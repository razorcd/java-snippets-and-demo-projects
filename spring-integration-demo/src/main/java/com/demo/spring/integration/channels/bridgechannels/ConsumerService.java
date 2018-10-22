package com.demo.spring.integration.channels.bridgechannels;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @ServiceActivator(inputChannel = "directChannel1")
    public void print(Message<String> message) {
        System.out.println(this.getClass().getSimpleName() + "-1: " + message.getPayload());
    }

    @ServiceActivator(inputChannel = "directChannel1")
    public void print2(Message<String> message) {
        System.out.println(this.getClass().getSimpleName() + "-2: " + message.getPayload());
    }

    @ServiceActivator(inputChannel = "directChannel1")
    public void print3(Message<String> message) {
        System.out.println(this.getClass().getSimpleName() + "-3: " + message.getPayload());
    }
}
