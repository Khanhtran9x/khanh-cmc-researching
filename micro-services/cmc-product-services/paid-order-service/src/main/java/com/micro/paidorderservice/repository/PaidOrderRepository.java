package com.micro.paidorderservice.repository;

import com.micro.paidorderservice.entity.PaidOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaidOrderRepository extends JpaRepository<PaidOrderEntity, Long> {
}
