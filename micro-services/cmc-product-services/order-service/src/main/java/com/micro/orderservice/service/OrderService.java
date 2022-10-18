package com.micro.orderservice.service;

import com.micro.orderservice.VO.BrandEntity;
import com.micro.orderservice.VO.ProductBrandEntity;
import com.micro.orderservice.VO.ProductEntity;
import com.micro.orderservice.VO.ResponseOrderEntity;
import com.micro.orderservice.entity.OrderEntity;
import com.micro.orderservice.entity.PaidOrderServerErrorEntity;
import com.micro.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaidOrderErrorService paidOrderErrorService;
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
        try {
            restTemplate.postForObject("http://PAID-ORDER-SERVICE/paid-orders", orderEntity, Boolean.class);
            log.info("Send paidOrder to PaidOrderService for saving successfully");
        } catch (Exception e) {
            log.info("PaidOrderService is down, temporarily save to paid_order_server_error table");
            PaidOrderServerErrorEntity paidOrderServerErrorEntity = new PaidOrderServerErrorEntity();
            paidOrderServerErrorEntity.setProductId(orderEntity.getProductId());
            paidOrderServerErrorEntity.setOrderNumbers(orderEntity.getOrderNumbers());
            paidOrderServerErrorEntity.setPaymentStatus(orderEntity.getPaymentStatus());
            paidOrderErrorService.saveToPaidOrderErrorTable(paidOrderServerErrorEntity);
        }
    }

    public ResponseOrderEntity getOrderInfo(Long id) {
        ResponseOrderEntity responseOrderEntity = new ResponseOrderEntity();
        OrderEntity orderEntity = orderRepository.findById(id).get();
        ProductBrandEntity productBrandEntity =
                restTemplate.getForObject("http://PRODUCT-SERVICE/products/" + orderEntity.getProductId(),
                ProductBrandEntity.class);

        responseOrderEntity.setOrderEntity(orderEntity);
        responseOrderEntity.setProductBrandEntity(productBrandEntity);
        return responseOrderEntity;
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }
}
