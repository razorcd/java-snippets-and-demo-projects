package com.demo.spring.integration.channels.bridgechannels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.BridgeFrom;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@Configuration
@IntegrationComponentScan
@EnableIntegration
public class BridgeApplication implements ApplicationRunner {

    /**
     * Channels.    producers --> pubSubChannel1 --> directChannel1 --> consumers
     */
    @Bean("pubSubChannel1")
    public MessageChannel createPubSubChannel1() {
        return new PublishSubscribeChannel();
    }

    @Bean("directChannel1")
    @BridgeFrom(value = "pubSubChannel1")
    public MessageChannel createDirectChannel1() {
        return new DirectChannel();
    }


    @Autowired
    @Qualifier("pubSubChannel1")
    public MessageChannel messageChannel;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Message<String> message = MessageBuilder.withPayload("first test data").build();
        messageChannel.send(message);
    }

    public static void main(String[] args) {
        SpringApplication.run(BridgeApplication.class, args);
    }
}
