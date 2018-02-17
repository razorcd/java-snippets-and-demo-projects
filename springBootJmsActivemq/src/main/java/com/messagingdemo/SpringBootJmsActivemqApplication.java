package com.messagingdemo;

import com.messagingdemo.listener.SomeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;

@SpringBootApplication
@EnableJms // triggers the discovery of methods marked with the @JmsListener and creates the listeners themselves behind the scenes
@Controller
public class SpringBootJmsActivemqApplication {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue stringMessagesQueue;

    @Autowired
    Queue objectMessagesQueue;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJmsActivemqApplication.class, args);
	}

	@RequestMapping(path = "/mymessages", method = RequestMethod.POST)
//    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
	public void postMyMessages(@RequestBody String myMessage) {
	    jmsTemplate.convertAndSend(stringMessagesQueue, myMessage);   // publish the message to QUEUE
        System.out.println("Controller received and published text:" + myMessage);

    }

    @RequestMapping(path = "/mymessageobjects", method = RequestMethod.POST)
//    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void postMyMessageObjects(@RequestBody SomeObject myMessageObject) {
        jmsTemplate.convertAndSend(objectMessagesQueue, myMessageObject);   // publish the message to QUEUE
        System.out.println("Controller received and published object:" + myMessageObject);

    }
}
