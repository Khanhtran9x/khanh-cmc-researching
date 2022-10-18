package com.micro.paidorderservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductBrandEntity {
    private ProductEntity productEntity;
    private BrandEntity brandEntity;
}
