package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class DemoController {

    private List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public SseEmitter getData() {
        SseEmitter sseEmitter = new SseEmitter(9999999999999L);

        emitters.add(sseEmitter);
        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));

        return sseEmitter;
    }

    @RequestMapping(value= "/newdata", method = RequestMethod.POST)
    @ResponseBody
    public void addData(@RequestBody String value) {
        emitData(value);

    }

    @RequestMapping(value = "/emitrandom", method = RequestMethod.POST)
    @ResponseBody
    public void emitRandom() {
        emitRandomData();
    }

    private void emitRandomData() {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("tick");
                    int i = new Random().nextInt();
                    emitData(i);
                }
            }, 0, 5000);
    }

    private synchronized void emitData(Object value) {
        try {
            for (SseEmitter sseEmitter : emitters) {
                sseEmitter.send(SseEmitter.event().name("mydata").data(value));    // name is like a channel
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
