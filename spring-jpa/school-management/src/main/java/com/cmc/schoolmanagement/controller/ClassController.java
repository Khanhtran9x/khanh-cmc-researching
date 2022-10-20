package com.cmc.schoolmanagement.controller;

import com.cmc.schoolmanagement.entity.ClassEntity;
import com.cmc.schoolmanagement.entity.StudentEntity;
import com.cmc.schoolmanagement.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/classes")
public class ClassController {
    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<ClassEntity> addNewClass(@RequestBody ClassEntity classEntity) {
        return new ResponseEntity<>(classService.saveClass(classEntity), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClassEntity>> getAllClasses() {
        return new ResponseEntity<>(classService.getAllClasses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassEntity> getClassById(@PathVariable("id") Long id) {
        Optional<ClassEntity> classEntity = classService.getClassById(id);
        return classEntity.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND):new ResponseEntity<>(classEntity.get(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClassEntity> updateClass(@RequestBody ClassEntity classEntity) {
        return new ResponseEntity<>(classService.updateClass(classEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClassEntity> deleteClass(@PathVariable Long id) {
        Optional<ClassEntity> classEntity = classService.getClassById(id);
        if (classEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        classService.deleteClass(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
