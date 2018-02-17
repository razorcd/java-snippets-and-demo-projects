package com.messagingdemo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import java.util.Arrays;

@Configuration
public class MessageQueueConfig {

    @Value("${activemq.url}")
    private String activemqUrl;

    @Bean
    public Queue stringMessagesQueue() {
        return new ActiveMQQueue("myMessagesQueue");
    }

    @Bean
    public Queue objectMessagesQueue() {
        return new ActiveMQQueue("myMessagesObjectQueue");
    }



    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(activemqUrl);
        activeMQConnectionFactory.setTrustedPackages(Arrays.asList("*")); // or specific:  "com.messagingdemo"
        return activeMQConnectionFactory;
    }

    @Bean
    JmsTemplate jmsTemplate() {
        return new JmsTemplate(activeMQConnectionFactory());
    }




    // Only required due to defining myMessageFactory in the receiver
    @Bean
    public JmsListenerContainerFactory<?> myMessageFactory(
            ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // anonymous class
        factory.setErrorHandler(
                new ErrorHandler() {
                    @Override
                    public void handleError(Throwable t) {
                        System.err.println("An error has occurred in the transaction. " + t.getMessage());
                    }
                });
        // lambda function
        factory.setErrorHandler(t -> System.err.println("An error has occurred in the transaction. " + t.getMessage()));
        configurer.configure(factory, connectionFactory);
        return factory;
    }




//    @Bean
//    public DefaultMessageHandlerMethodFactory myMessageFactory() {
//        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
//        factory.setMessageConverter(messageConverter());
//        return factory;
//    }

//    @Override
//    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
//        registrar.setMessageHandlerMethodFactory(myMessageFactory());
//    }    // Only required due to defining myMessageFactory in the receiver
//    @Bean
//    public JmsListenerContainerFactory<?> myMessageFactory(
//            ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        // anonymous class
//        factory.setErrorHandler(
//                new ErrorHandler() {
//                    @Override
//                    public void handleError(Throwable t) {
//                        System.err.println("An error has occurred in the transaction. " + t.getMessage());
//                    }
//                });
//        // lambda function
//        factory.setErrorHandler(t -> System.err.println("An error has occurred in the transaction. " + t.getMessage()));
//        configurer.configure(factory, connectionFactory);
//        return factory;
//    }



    // Serialize message content to json with TextMessage, using Jackson
    @Bean
    public MessageConverter messageConverter() {
        MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
