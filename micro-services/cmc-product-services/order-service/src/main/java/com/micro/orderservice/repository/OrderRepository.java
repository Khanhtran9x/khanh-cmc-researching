package com.micro.orderservice.repository;

import com.micro.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Modifying
    @Query(value = "update order_product set payment_status = 1 where order_id =:id", nativeQuery = true)
    void completePayment(@Param("id") Long id);
}
