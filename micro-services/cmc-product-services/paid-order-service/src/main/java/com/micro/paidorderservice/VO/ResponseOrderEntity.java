package com.micro.paidorderservice.VO;

import com.micro.paidorderservice.entity.PaidOrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOrderEntity {
    private PaidOrderEntity paidOrderEntity;
    private ProductBrandEntity productBrandEntity;
}
