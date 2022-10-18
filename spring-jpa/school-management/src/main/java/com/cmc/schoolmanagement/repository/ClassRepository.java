package com.cmc.schoolmanagement.repository;

import com.cmc.schoolmanagement.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
}
