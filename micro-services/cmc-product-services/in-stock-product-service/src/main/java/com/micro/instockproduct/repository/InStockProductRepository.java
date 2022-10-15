package com.micro.instockproduct.repository;

import com.micro.instockproduct.entity.InStockProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InStockProductRepository extends JpaRepository<InStockProductEntity, Long> {
}
