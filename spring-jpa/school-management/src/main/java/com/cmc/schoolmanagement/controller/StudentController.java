package com.cmc.schoolmanagement.controller;

import com.cmc.schoolmanagement.entity.StudentEntity;
import com.cmc.schoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentEntity> addNewStudent(@RequestBody StudentEntity studentEntity) {
        return new ResponseEntity<>(studentService.saveStudent(studentEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long id) {
        Optional<StudentEntity> studentEntity = studentService.findById(id);
        return studentEntity.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND):new ResponseEntity<>(studentEntity.get(), HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<StudentEntity> updateStudent(@RequestBody StudentEntity studentEntity) {
        return new ResponseEntity<>(studentService.updateStudent(studentEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentEntity> deleteStudent(@PathVariable Long id) {
        Optional<StudentEntity> studentEntity = studentService.findById(id);
        if (studentEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.deleteStudents(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
