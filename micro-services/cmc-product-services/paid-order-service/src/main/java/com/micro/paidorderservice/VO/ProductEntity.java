package com.micro.paidorderservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    private Long productId;
    private String productCode;
    private String productName;
    private Long brandId;
}
