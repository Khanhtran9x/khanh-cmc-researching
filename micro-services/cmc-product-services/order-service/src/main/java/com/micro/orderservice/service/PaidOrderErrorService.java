package com.micro.orderservice.service;

import com.micro.orderservice.entity.PaidOrderServerErrorEntity;
import com.micro.orderservice.repository.PaidOrderErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaidOrderErrorService {
    @Autowired
    private PaidOrderErrorRepository paidOrderErrorRepository;

    public void saveToPaidOrderErrorTable(PaidOrderServerErrorEntity paidOrderServerErrorEntity) {
        paidOrderErrorRepository.save(paidOrderServerErrorEntity);
    }
}
