package com.micro.instockproduct.service;

import com.micro.instockproduct.entity.InStockProductEntity;
import com.micro.instockproduct.repository.InStockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InStockProductService {
    @Autowired
    private InStockProductRepository inStockProductRepository;

    public InStockProductEntity createNewProduct(InStockProductEntity inStockProductEntity) {
        return inStockProductRepository.save(inStockProductEntity);
    }
}
