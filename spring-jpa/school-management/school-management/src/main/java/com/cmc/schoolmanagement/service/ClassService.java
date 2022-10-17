package com.cmc.schoolmanagement.service;

import com.cmc.schoolmanagement.entity.ClassEntity;
import com.cmc.schoolmanagement.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    public ClassEntity saveClass(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }
}
