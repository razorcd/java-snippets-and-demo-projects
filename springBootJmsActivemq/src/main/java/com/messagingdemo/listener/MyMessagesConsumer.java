package com.messagingdemo.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyMessagesConsumer {

    @JmsListener(destination = "myMessagesQueue")
    public void myMessagesConsumer(String message) {
        System.out.println("Listener received: " + message);
    }




}
