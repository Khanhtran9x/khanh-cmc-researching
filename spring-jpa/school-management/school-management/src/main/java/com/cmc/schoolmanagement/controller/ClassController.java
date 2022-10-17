package com.cmc.schoolmanagement.controller;

import com.cmc.schoolmanagement.entity.ClassEntity;
import com.cmc.schoolmanagement.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classes")
public class ClassController {
    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<ClassEntity> addNewClass(@RequestBody ClassEntity classEntity) {
        return new ResponseEntity<>(classService.saveClass(classEntity), HttpStatus.CREATED);
    }
}
