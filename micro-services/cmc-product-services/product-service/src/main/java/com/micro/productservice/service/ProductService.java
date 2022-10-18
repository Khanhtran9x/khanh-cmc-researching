package com.micro.productservice.service;

import com.micro.productservice.VO.BrandEntity;
import com.micro.productservice.VO.ProductBrandEntity;
import com.micro.productservice.entity.ProductEntity;
import com.micro.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestTemplate restTemplate;

    public ProductEntity createNew(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public ProductBrandEntity getProductWithBrandInfo(Long productId) {
        ProductBrandEntity productBrandEntity = new ProductBrandEntity();
        ProductEntity productEntity = productRepository.findById(productId).get();
        BrandEntity brandEntity =
                restTemplate.getForObject("http://BRAND-SERVICE/brands/" + productEntity.getBrandId(),BrandEntity.class);
        productBrandEntity.setProductEntity(productEntity);
        productBrandEntity.setBrandEntity(brandEntity);
        return productBrandEntity;
    }

    public List<ProductEntity> getAllProduct() {
        return productRepository.findAll();
    }
}
