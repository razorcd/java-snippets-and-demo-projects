package com.messagingdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;

@SpringBootApplication
@Controller
public class SpringBootJmsActivemqApplication {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJmsActivemqApplication.class, args);
	}

	@RequestMapping(path = "/mymessages", method = RequestMethod.POST)
//    @ResponseBody
    @ResponseStatus(value = HttpStatus.ACCEPTED)
	public void postMyMessages(@RequestBody String myMessage) {
	    jmsTemplate.convertAndSend(queue, myMessage);   // publish the message to activeMq
        System.out.println("Controller received and published:" + myMessage);

    }
}
