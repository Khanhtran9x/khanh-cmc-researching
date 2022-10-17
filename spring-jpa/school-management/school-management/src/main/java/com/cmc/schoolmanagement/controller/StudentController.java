package com.cmc.schoolmanagement.controller;

import com.cmc.schoolmanagement.entity.StudentEntity;
import com.cmc.schoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentEntity> addNewStudent (@RequestBody StudentEntity studentEntity) {
        return new ResponseEntity<>(studentService.saveStudent(studentEntity), HttpStatus.CREATED);
    }
}
