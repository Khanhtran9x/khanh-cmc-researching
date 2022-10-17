package com.cmc.schoolmanagement.controller;

import com.cmc.schoolmanagement.entity.StudentEntity;
import com.cmc.schoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentEntity> addNewStudent (@RequestBody StudentEntity studentEntity) {
        return new ResponseEntity<>(studentService.saveStudent(studentEntity), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById (@PathVariable Long id) {
        Optional<StudentEntity> studentEntity = studentService.findById(id);
        return studentEntity == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND):new ResponseEntity<>(studentEntity.get(), HttpStatus.NOT_FOUND);

    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Long id, @RequestBody StudentEntity studentEntity) {
        return new ResponseEntity<>(studentService.updateStudent(id, studentEntity), HttpStatus.OK);
    }
}
