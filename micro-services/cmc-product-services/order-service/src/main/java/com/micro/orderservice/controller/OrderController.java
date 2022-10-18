package com.micro.orderservice.controller;

import com.micro.orderservice.VO.ResponseOrderEntity;
import com.micro.orderservice.constants.KafkaConstants;
import com.micro.orderservice.entity.OrderEntity;
import com.micro.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private KafkaTemplate<String, OrderEntity> kafkaTemplate;

    @PostMapping
    public void order(@RequestBody OrderEntity orderEntity) {
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC_ORDER, orderEntity).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/complete-payment/{id}")
    public Boolean completePayment(@PathVariable Long id) {
        orderService.completePayment(id);
        return true;
    }

    @GetMapping("/{id}")
    public ResponseOrderEntity getOrderInfo(@PathVariable("id") Long id) {
        return orderService.getOrderInfo(id);
    }
}
