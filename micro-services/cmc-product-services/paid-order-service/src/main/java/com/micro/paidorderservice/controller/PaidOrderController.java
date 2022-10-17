package com.micro.paidorderservice.controller;

import com.micro.paidorderservice.constants.KafkaConstants;
import com.micro.paidorderservice.entity.PaidOrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/paid-orders")
@Slf4j
public class PaidOrderController {
    @Autowired
    private KafkaTemplate<String, PaidOrderEntity> kafkaTemplate;

    @PostMapping
    public Boolean getNewPaidOrder(@RequestBody PaidOrderEntity paidOrderEntity) {
        log.info("Inside paid getNewPaidOrder method in controller");
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC_PAID, paidOrderEntity).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
