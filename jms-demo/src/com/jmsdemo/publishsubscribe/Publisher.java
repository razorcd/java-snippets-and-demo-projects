package com.jmsdemo.publishsubscribe;


import javax.jms.*;
import javax.naming.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.log4j.BasicConfigurator;

public class Publisher {

    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // Name of the topic we will receive messages from
    private static String topicName = "MyTopic";


    private TopicSession pubSession;
    private TopicPublisher publisher;
    private TopicConnection connection;
    /* Establish JMS publisher and subscriber */

    public Publisher(String topicName, String username, String password) throws Exception {

        // Obtain a JNDI connection
        InitialContext jndi = new InitialContext();
        // Look up a JMS connection factory
//        TopicConnectionFactory conFactory = (TopicConnectionFactory) jndi
//                .lookup("topicConnectionFactry");
        ActiveMQConnectionFactory conFactory = new ActiveMQConnectionFactory(url); // define ConnectionFactory explicitly


        // Create a JMS connection
        connection = conFactory.createTopicConnection(username, password);

        // Create JMS session objects for publisher and subscriber
        pubSession = connection.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);
        TopicSession subSession = connection.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);

        // Look up a JMS topic
        Topic chatTopic = new ActiveMQTopic(topicName);

        // Create a JMS publisher and subscriber
        publisher = pubSession.createPublisher(chatTopic);

        // Start the JMS connection; allows messages to be delivered
        connection.start();

        // Create and send message using topic publisher
        TextMessage message = pubSession.createTextMessage();
        message.setText(username + ": !!! some text !!!");
        publisher.publish(message);
        System.out.println("\nPublished message: " + message);
    }

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        Publisher demo = new Publisher("name1", "user1", "password1");
    }
}