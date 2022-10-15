package com.micro.productservice.service;

import com.micro.productservice.VO.BrandEntity;
import com.micro.productservice.VO.ResponseTemplate;
import com.micro.productservice.entity.ProductEntity;
import com.micro.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestTemplate restTemplate;

    public ProductEntity createNew(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public ResponseTemplate getProductWithBrandInfo(Long productId) {
        ResponseTemplate responseTemplate = new ResponseTemplate();
        ProductEntity productEntity = productRepository.findById(productId).get();
        BrandEntity brandEntity =
                restTemplate.getForObject("http://localhost:9003/brands/" + productEntity.getBrandId(),BrandEntity.class);
        responseTemplate.setProductEntity(productEntity);
        responseTemplate.setBrandEntity(brandEntity);
        return responseTemplate;
    }
}
