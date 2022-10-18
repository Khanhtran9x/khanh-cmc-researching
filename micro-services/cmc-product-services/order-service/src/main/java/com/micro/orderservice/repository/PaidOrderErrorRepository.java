package com.micro.orderservice.repository;

import com.micro.orderservice.entity.PaidOrderServerErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaidOrderErrorRepository extends JpaRepository<PaidOrderServerErrorEntity, Long> {
}
