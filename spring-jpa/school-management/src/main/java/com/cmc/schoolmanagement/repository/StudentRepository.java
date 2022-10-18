package com.cmc.schoolmanagement.repository;

import com.cmc.schoolmanagement.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    @Query(value = "select * from student where class_id = :classId", nativeQuery = true)
    Set<StudentEntity> getStudentsInClass(@Param("classId") Long classId);
}
