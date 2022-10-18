package com.micro.paidorderservice.service;

import com.micro.paidorderservice.VO.ProductBrandEntity;
import com.micro.paidorderservice.VO.ResponseOrderEntity;
import com.micro.paidorderservice.entity.PaidOrderEntity;
import com.micro.paidorderservice.repository.PaidOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PaidOrderService {
    @Autowired
    private PaidOrderRepository paidOrderRepository;
    @Autowired
    private RestTemplate restTemplate;

    public PaidOrderEntity savePaidOrder(PaidOrderEntity paidOrderEntity) {
        return paidOrderRepository.save(paidOrderEntity);
    }

    public ResponseOrderEntity getPaidOrderInfo(Long id) {
        ResponseOrderEntity responseOrderEntity = new ResponseOrderEntity();
        PaidOrderEntity paidOrderEntity = paidOrderRepository.findById(id).get();
        ProductBrandEntity productBrandEntity =
                restTemplate.getForObject("http://PRODUCT-SERVICE/products/" + paidOrderEntity.getProductId(),
                        ProductBrandEntity.class);

        responseOrderEntity.setPaidOrderEntity(paidOrderEntity);
        responseOrderEntity.setProductBrandEntity(productBrandEntity);
        return responseOrderEntity;
    }

    public List<PaidOrderEntity> getAllPaidOrders() {
        return paidOrderRepository.findAll();
    }
}
