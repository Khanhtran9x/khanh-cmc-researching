package com.micro.orderservice.VO;

import com.micro.orderservice.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOrderEntity {
    private OrderEntity orderEntity;
    private ProductBrandEntity productBrandEntity;
}
