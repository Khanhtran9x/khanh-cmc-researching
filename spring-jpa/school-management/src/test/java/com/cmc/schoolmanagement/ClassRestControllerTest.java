package com.cmc.schoolmanagement;

import com.cmc.schoolmanagement.controller.ClassController;
import com.cmc.schoolmanagement.entity.ClassEntity;
import com.cmc.schoolmanagement.service.ClassService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClassRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClassService classService;

    @Test
    public void whenPostEmployeeThenCreateEmployee() throws Exception {
        ClassEntity classEntity = new ClassEntity(1L, "12A1");
        BDDMockito.given(classService.saveClass(Mockito.any())).willReturn(classEntity);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/classes")
                        .content(this.objectMapper.writeValueAsString(classEntity))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.className", is(classEntity.getClassName())));

        verify(classService, VerificationModeFactory.times(1)).saveClass(Mockito.any());
        reset(classService);
    }

    @Test
    public void givenClassesWhenGetClassesThenReturnJsonArray() throws Exception {
        ClassEntity classEntity1 = new ClassEntity(1L, "12A1");
        ClassEntity classEntity2 = new ClassEntity(2L, "12A2");
        ClassEntity classEntity3 = new ClassEntity(3L, "12A3");

        List<ClassEntity> classEntities = Arrays.asList(classEntity1, classEntity2, classEntity3);

        BDDMockito.given(classService.getAllClasses()).willReturn(classEntities);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/classes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].className", is(classEntity1.getClassName())))
                .andExpect(jsonPath("$[1].className", is(classEntity2.getClassName())))
                .andExpect(jsonPath("$[2].className", is(classEntity3.getClassName())));

        verify(classService, VerificationModeFactory.times(1)).getAllClasses();
        reset(classService);
    }

    @Test
    public void whenValidClassIdThenShouldReturnClass() throws Exception {
        ClassEntity classEntity = new ClassEntity(1L, "12A1");
        BDDMockito.given(classService.getClassById(Mockito.any())).willReturn(Optional.of(classEntity));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/classes/" + classEntity.getClassId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.className", is(classEntity.getClassName())));

        verify(classService, VerificationModeFactory.times(1)).getClassById(Mockito.any());
        reset(classService);
    }

    @Test
    public void whenDeleteClass() throws Exception {
        ClassEntity classEntity = new ClassEntity(1L, "12A1");
        doNothing().when(classService).deleteClass(Mockito.any());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/classes/" + classEntity.getClassId()))
                .andDo(print())
                .andExpect(status().isOk());

        verify(classService, VerificationModeFactory.times(1)).deleteClass(Mockito.any());
        reset(classService);
    }
}
