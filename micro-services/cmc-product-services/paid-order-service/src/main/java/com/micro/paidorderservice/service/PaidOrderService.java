package com.micro.paidorderservice.service;

import com.micro.paidorderservice.entity.PaidOrderEntity;
import com.micro.paidorderservice.repository.PaidOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaidOrderService {
    @Autowired
    private PaidOrderRepository paidOrderRepository;

    public PaidOrderEntity savePaidOrder(PaidOrderEntity paidOrderEntity) {
        return paidOrderRepository.save(paidOrderEntity);
    }
}
