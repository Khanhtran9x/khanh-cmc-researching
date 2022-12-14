package com.micro.orderservice.listener;

import com.micro.orderservice.constants.KafkaConstants;
import com.micro.orderservice.entity.OrderEntity;
import com.micro.orderservice.entity.PaidOrderServerErrorEntity;
import com.micro.orderservice.service.OrderService;
import com.micro.orderservice.service.PaidOrderErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class OrderListener {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PaidOrderErrorService paidOrderErrorService;
    @Autowired
    private KafkaTemplate<String, OrderEntity> kafkaTemplate;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC_ORDER,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(OrderEntity orderEntity) {
        log.info("Listening new order");
        if (!orderEntity.getPaymentStatus()) {
            log.info("Order has not been paid yet, save to database");
            orderService.order(orderEntity);
        } else {
            log.info("Order has been paid, sending to PaidOrderService");
            orderService.sendPaidOrder(orderEntity);
        }
    }
}
