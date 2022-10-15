package com.micro.instockproduct.controller;

import com.micro.instockproduct.entity.InStockProductEntity;
import com.micro.instockproduct.service.InStockProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/in-stock-products")
public class InStockProductController {
    @Autowired
    private InStockProductService inStockProductService;

    @PostMapping
    public InStockProductEntity createNewProduct(@RequestBody InStockProductEntity inStockProductEntity) {
        return inStockProductService.createNewProduct(inStockProductEntity);
    }
}
