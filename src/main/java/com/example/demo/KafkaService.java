package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsg(String topic, Integer num) {
        ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send
                (topic, (num-1) % 3, String.valueOf(num), String.valueOf(num));
        result.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                // Continue to send the message when the previous one fails.
                sendMsg(topic, num);
            }

            @Override
            public void onSuccess(SendResult<String, String> stringSendResult) {
                System.out.println(String.format("Sent a message to kafka successfully, topic = %s, " +
                        "partition = %s, value = %s", stringSendResult.getProducerRecord().topic(),
                        stringSendResult.getProducerRecord().partition(),
                        stringSendResult.getProducerRecord().value()));
            }
        });
    }

}
