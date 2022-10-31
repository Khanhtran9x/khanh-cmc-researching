package com.cmc.schoolmanagement;

import com.cmc.schoolmanagement.entity.ClassEntity;
import com.cmc.schoolmanagement.repository.ClassRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@DataJpaTest
public class ClassRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ClassRepository classRepository;

    @Test
    public void whenFindByIdThenReturnClass() {
        ClassEntity classEntity = new ClassEntity(1L, "10A1");
        this.entityManager.persist(classEntity);

        ClassEntity fromDb = classRepository.findById(classEntity.getClassId()).orElse(null);
        assertEquals(fromDb.getClassName(), classEntity.getClassName());
    }

    @Test
    public void whenInvalidIdThenReturnNull() {
        ClassEntity classEntity = classRepository.findById(-1L).orElse(null);
        assertNull(classEntity);
    }
}
