package com.demo.spring.integration.channels.bridgechannels;

import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

@Service
public class PollerService {

    //    @ServiceActivator(inputChannel = "channel1")
    @InboundChannelAdapter(value = "pubSubChannel1", poller = @Poller(fixedDelay = "1000"))
    public Message<String> poll() {

        // poll data regularly from external source
        return new GenericMessage<>("poller message");
    }
}
