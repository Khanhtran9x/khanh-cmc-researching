package com.micro.brandservice.service;

import com.micro.brandservice.entity.BrandEntity;
import com.micro.brandservice.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public BrandEntity createNew(BrandEntity brandEntity) {
        return brandRepository.save(brandEntity);
    }

    public BrandEntity getBrandById(Long brandId) {
        return brandRepository.findById(brandId).get();
    }
}
