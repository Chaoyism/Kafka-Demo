package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/kafka")
public class Controller {
    private Set<String> comsumedMessage = new HashSet<>();

    private String messagePrefix;

    @Value("${kafka.topic}")
    private String topic;

    @Value("${min}")
    private int minNum;

    @Value("${max}")
    private int maxNum;

    @Autowired
    private KafkaService kafkaService;

    @RequestMapping("/send")
    public String send() {
        Long startTime = System.currentTimeMillis();
        messagePrefix = "" + startTime;
        for (int i = minNum; i <= maxNum; i++) {
            kafkaService.sendMsg(topic, i);
        }
    }







}
