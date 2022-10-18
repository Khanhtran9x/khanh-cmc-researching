package com.cmc.schoolmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String studentCode;
    private String studentFirstName;
    private String studentLastName;
    @Column(columnDefinition = "DATE")
    private String studentDateOfBirth;
    private String studentEmail;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="class_id", nullable=false)
    private ClassEntity classEntity;
}
