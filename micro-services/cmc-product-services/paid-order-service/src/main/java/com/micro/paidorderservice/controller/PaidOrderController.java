package com.micro.paidorderservice.controller;

import com.micro.paidorderservice.VO.ResponseOrderEntity;
import com.micro.paidorderservice.constants.KafkaConstants;
import com.micro.paidorderservice.entity.PaidOrderEntity;
import com.micro.paidorderservice.service.PaidOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/paid-orders")
@Slf4j
public class PaidOrderController {
    @Autowired
    private PaidOrderService paidOrderService;
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

    @GetMapping
    public List<PaidOrderEntity> getAllPaidOrders() {
        return paidOrderService.getAllPaidOrders();
    }

    @GetMapping("/{id}")
    public ResponseOrderEntity getOrderInfo(@PathVariable("id") Long id) {
        return paidOrderService.getPaidOrderInfo(id);
    }
}
