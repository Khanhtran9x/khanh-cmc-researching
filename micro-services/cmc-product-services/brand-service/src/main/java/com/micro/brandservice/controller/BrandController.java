package com.micro.brandservice.controller;

import com.micro.brandservice.entity.BrandEntity;
import com.micro.brandservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping
    public BrandEntity createNew(@RequestBody BrandEntity brandEntity) {
        return brandService.createNew(brandEntity);
    }

    @GetMapping("/{id}")
    public BrandEntity getBrandById(@PathVariable("id") Long brandId) {
        return brandService.getBrandById(brandId);
    }

    @GetMapping
    public List<BrandEntity> getAllBrands() {
        return brandService.getALlBrands();
    }
}
