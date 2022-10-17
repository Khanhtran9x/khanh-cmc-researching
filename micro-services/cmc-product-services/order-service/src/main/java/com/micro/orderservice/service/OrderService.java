package com.micro.orderservice.service;

import com.micro.orderservice.entity.OrderEntity;
import com.micro.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

    public OrderEntity order(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    public void completePayment(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).get();
        orderRepository.delete(orderEntity);
        restTemplate.postForObject("http://PAID-ORDER-SERVICE/paid-orders", orderEntity, Boolean.class);
    }

    public void sendPaidOrder(OrderEntity orderEntity) {
        System.out.println("inside paid order service");
        restTemplate.postForObject("http://PAID-ORDER-SERVICE/paid-orders", orderEntity, Boolean.class);
    }
}
