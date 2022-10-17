package com.micro.paidorderservice.listener;

import com.micro.paidorderservice.constants.KafkaConstants;
import com.micro.paidorderservice.entity.PaidOrderEntity;
import com.micro.paidorderservice.service.PaidOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaidOrderListener {
    @Autowired
    private PaidOrderService paidOrderService;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC_PAID,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(PaidOrderEntity paidOrderEntity) {
        log.info("Listening new paid order");
        paidOrderService.savePaidOrder(paidOrderEntity);
    }
}
