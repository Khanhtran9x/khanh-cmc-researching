package com.micro.productservice.controller;

import com.micro.productservice.VO.ProductBrandEntity;
import com.micro.productservice.entity.ProductEntity;
import com.micro.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductEntity createNew(@RequestBody ProductEntity productEntity) {
        return productService.createNew(productEntity);
    }

    @GetMapping("/{id}")
    public ProductBrandEntity getProductWithBrandInfo(@PathVariable("id") Long productId) {
        return productService.getProductWithBrandInfo(productId);
    }
}
