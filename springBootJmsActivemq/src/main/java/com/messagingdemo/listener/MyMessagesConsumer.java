package com.messagingdemo.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyMessagesConsumer {

    @JmsListener(destination = "myMessagesQueue", containerFactory = "myMessageFactory")
//    @JmsListener(destination = "myMessagesQueue")
    public void myMessagesConsumer(String message) {
        System.out.println("Listener received text: " + message);
    }

    @JmsListener(destination = "myMessagesObjectQueue", containerFactory = "myMessageFactory")
//    @JmsListener(destination = "myMessagesObjectQueue")
    public void myMessageObjectsConsumer(SomeObject someobjectMessage) {
        System.out.println("Listener received object: " + someobjectMessage.toString());
    }



}
