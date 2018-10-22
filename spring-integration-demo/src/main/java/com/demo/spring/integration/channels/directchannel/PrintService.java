package com.demo.spring.integration.channels.directchannel;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class PrintService {

//    @Autowired
//    @Qualifier("channel2")
//    DirectChannel directChannel;

    @ServiceActivator(inputChannel = "channel1", outputChannel = "channel2")
    public String print(Message<String> message) {
        message.getHeaders()
                .forEach((key, value) -> System.out.println(key + " : " + value));
        System.out.println(message.getPayload());

//        return MessageBuilder.withPayload("PRINT FINISHED").build();
        return "PRINT FINISHED";

//        directChannel.send(responseMessage);
    }
}
