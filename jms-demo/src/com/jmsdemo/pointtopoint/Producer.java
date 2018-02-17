package com.jmsdemo.pointtopoint;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.BasicConfigurator;

import javax.naming.NamingException;
import javax.jms.*;



public class Producer {

    // URL of the JMS server
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    // Name of the queue we will receive messages from
    private static String subject = "MyQueue";


    public Producer() throws JMSException, NamingException {

        // Look up a JMS connection factory
        ConnectionFactory conFactory = new ActiveMQConnectionFactory(url); // define ConnectionFactory explicitly

        Connection connection;
        // Getting JMS connection from the server and starting it
        connection = conFactory.createConnection();

        BasicConfigurator.configure();
        // Getting JMS connection from the server

        try {
            connection.start();
            // JMS messages are sent and received using a Session. We will
            // create here a non-transactional session object. If you want
            // to use transactions you should set the first parameter to 'true'
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);

//            Destination destination = (Destination) jndi.lookup("MyQueue");
            Destination destination = session.createQueue(subject);


            // MessageProducer is used for sending messages (as opposed
            // to MessageConsumer which is used for receiving them)
            MessageProducer producer = session.createProducer(destination);
            // We will send a small text message saying 'Hello World!'
            TextMessage message = session.createTextMessage("!!!! Message from producer !!!!");
            // Here we are sending the message!
            producer.send(message);
            System.out.println("Sent message '" + message.getText() + "'");
        } finally {
            connection.close();
        }
    }

    public static void main(String[] args) throws JMSException {
        try {
            BasicConfigurator.configure();
            new Producer();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
}