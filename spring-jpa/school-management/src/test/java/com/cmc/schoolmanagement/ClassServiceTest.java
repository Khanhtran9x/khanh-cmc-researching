package com.cmc.schoolmanagement;

import com.cmc.schoolmanagement.entity.ClassEntity;
import com.cmc.schoolmanagement.repository.ClassRepository;
import com.cmc.schoolmanagement.service.ClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClassServiceTest {
    @Autowired
    private ClassService classService;

    @MockBean
    private ClassRepository classRepository;

    @BeforeEach
    public void setUp() {
        ClassEntity classEntity1 = new ClassEntity(1L,"12A1");
        ClassEntity classEntity2 = new ClassEntity(2L, "12A2");
        ClassEntity classEntity3 = new ClassEntity(3L, "12A3");

        List<ClassEntity> classEntities = Arrays.asList(classEntity1, classEntity2, classEntity3);
        Mockito.when(classRepository.findByClassName(classEntity1.getClassName())).thenReturn(classEntity1);
        Mockito.when(classRepository.findByClassName(classEntity2.getClassName())).thenReturn(classEntity2);
        Mockito.when(classRepository.findById(classEntity1.getClassId())).thenReturn(Optional.of(classEntity1));
        Mockito.when(classRepository.findByClassName(classEntity3.getClassName())).thenReturn(classEntity3);
        Mockito.when(classRepository.findAll()).thenReturn(classEntities);
    }

    @Test
    public void whenValidNameThenClassShouldBeFound() {
        String className = "12A1";

        ClassEntity fromDb = classService.getClassByName(className);
        assertEquals(fromDb.getClassName(), className);
    }

    @Test
    public void whenInvalidNameThenClassShouldNotBeFound() {
        String className = "10A1";

        ClassEntity fromDb = classService.getClassByName(className);
        assertNull(fromDb);
    }

    @Test
    public void whenValidIdThenClassShouldBeFound() {
        ClassEntity classEntity = classService.getClassById(1L).orElse(null);
        assertEquals(classEntity.getClassName(), "12A1");
    }

    @Test
    public void whenInvalidIdThenClassShouldNotBeFound() {
        ClassEntity classEntity = classService.getClassById(5L).orElse(null);
        assertNull(classEntity);
    }

    @Test
    public void whenGetAllThenReturn3Records() {
        List<ClassEntity> classEntities = classService.getAllClasses();
        assertEquals(classEntities.size(), 3);
    }

    @Test
    public void whenDeleteClass() {
        classService.deleteClass(1L);
        Mockito.verify(classRepository).deleteById(1L);
    }
}
