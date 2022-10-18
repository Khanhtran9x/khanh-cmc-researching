package com.micro.paidorderservice.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity {
    private Long brandId;
    private String brandName;
    private String brandNation;
}
