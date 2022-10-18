package com.cmc.schoolmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "class")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;
    private String className;
    @OneToMany(mappedBy = "classEntity")
    @JsonIgnore
    private Set<StudentEntity> studentEntities;
}
