package com.jmsdemo.publishsubscribe;


import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.log4j.BasicConfigurator;

import javax.jms.*;
import javax.naming.InitialContext;

public class Subscriber implements MessageListener {

    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // Name of the topic we will receive messages from
    private static String topicName = "MyTopic";


    private TopicSession pubSession;
    private TopicPublisher publisher;
    private TopicConnection connection;
    /* Establish JMS publisher and subscriber */

    public Subscriber(String topicName, String username, String password) throws Exception {

        // Obtain a JNDI connection
        InitialContext jndi = new InitialContext();
        // Look up a JMS connection factory
//        TopicConnectionFactory conFactory = (TopicConnectionFactory) jndi.lookup("topicConnectionFactry");
        ActiveMQConnectionFactory conFactory = new ActiveMQConnectionFactory(url); // define ConnectionFactory explicitly


        // Create a JMS connection
        connection = conFactory.createTopicConnection(username, password);

        // Create JMS session objects for publisher and subscriber
        TopicSession subSession = connection.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);

        // Look up a JMS topic
        Topic chatTopic = new ActiveMQTopic(topicName);

        // Create a JMS publisher and subscriber
        TopicSubscriber subscriber = subSession.createSubscriber(chatTopic);    // will NOT pickup missed messages while offline
//        TopicSubscriber subscriber = subSession.createDurableSubscriber(chatTopic, "durableSub1");   // will NOT pickup missed messages while offline

        // Set a JMS message listener
        subscriber.setMessageListener(this);

        // Start the JMS connection; allows messages to be delivered
        connection.start();
    }

    /*
     * A client can register a message listener with a consumer. A message
     * listener is similar to an event listener. Whenever a message arrives at
     * the destination, the JMS provider delivers the message by calling the
     * listener's onMessage method, which acts on the contents of the message.
     */
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println("\nSubscriber received message: " + message);
            System.out.println("Text: " + text);

            Topic replyToTopic = (Topic) message.getJMSReplyTo();
            sendReply(replyToTopic);
        } catch (Exception e) {  // JMSException e
            e.printStackTrace();
        }
    }


    private void sendReply(Topic replyTopic) throws Exception {
        TopicSession subSession = connection.createTopicSession(false, Session.DUPS_OK_ACKNOWLEDGE);
        TopicPublisher publisher = subSession.createPublisher(replyTopic);
        TextMessage replyMessage = subSession.createTextMessage("Got it !!!!");
        System.out.println("Sending reply!");
        publisher.publish(replyMessage);
        // TODO: use callbacks!?
    }

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        Subscriber demo = new Subscriber("name1", "user1", "password1");
    }
}