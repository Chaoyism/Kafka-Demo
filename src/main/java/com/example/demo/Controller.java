package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/kafka")
public class Controller {
    private Set<String> consumedMessage = new HashSet<>();

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
        return "Sent messages successfully,  time cost = " + (System.currentTimeMillis() - startTime) + "ms,";
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMsg(ConsumerRecord<String, String> record, Acknowledgment ack) {
        if (consumedMessage.contains(messagePrefix + record.value())) {
            ack.acknowledge();
            return;
        }
        System.out.println(String.format("Consume a message successfully, topic = %s, partition = %s, offset = %s, value = %s", record.topic(), record.partition(), record.offset(), record.value()));
        consumedMessage.add(messagePrefix + record.value());
        ack.acknowledge();
    }

}
