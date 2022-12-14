package com.cmc.schoolmanagement.service;

import com.cmc.schoolmanagement.entity.StudentEntity;
import com.cmc.schoolmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentEntity saveStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    public Optional<StudentEntity> findById(Long id) {
        return studentRepository.findById(id);
    }

    public StudentEntity updateStudent(StudentEntity studentEntity) {
        return studentRepository.save(studentEntity);
    }

    public void deleteStudents(Long id) {
        studentRepository.deleteById(id);
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }
}
