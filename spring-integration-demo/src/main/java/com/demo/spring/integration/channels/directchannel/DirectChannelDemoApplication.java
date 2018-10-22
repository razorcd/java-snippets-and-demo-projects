package com.demo.spring.integration.channels.directchannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@Configuration
public class DirectChannelDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DirectChannelDemoApplication.class, args);
    }

    /**
     * Point to point channel.
     */
    @Bean("channel1")
    public DirectChannel createDirectChannel1() {
        return new DirectChannel();
    }
    @Bean("channel2")
    public DirectChannel createDirectChannel2() {
        return new DirectChannel();
    }


    @Autowired
    @Qualifier("channel1")
    private DirectChannel directChannel;


    @Override
//    @SuppressWarnings("unchecked")
    public void run(ApplicationArguments args) throws Exception {


          // replaced by "@ServiceActivator(inputChannel = "channel1")"
//        directChannel.subscribe(new MessageHandler() {
//            @Override
//            public void handleMessage(Message<?> message) throws MessagingException {
//                new PrintService().print((Message<String>)message);
//            }
//        });


        // #1
        Message<String> message1 = MessageBuilder
                .withPayload("RUNNED")
                .setHeader("test1", "testdata1")
                .build();

        directChannel.send(message1);


        // #1
        Message<String> message2 = MessageBuilder
                .withPayload("RUNNED WITH MESSAGE TEMPLATE")
                .build();

        MessagingTemplate template = new MessagingTemplate();
        template.sendAndReceive(directChannel, message2);
    }
}
