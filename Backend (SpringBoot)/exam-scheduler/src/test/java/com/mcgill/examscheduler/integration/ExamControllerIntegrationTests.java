package com.mcgill.examscheduler.integration;

import com.mcgill.examscheduler.exam.model.Exam;
import com.mcgill.examscheduler.exam.repo.ExamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ExamControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetExams() throws Exception {
        mockMvc.perform(get("/api/v1/exam"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    public void testGetExamByClassName() throws Exception {
        mockMvc.perform(get("/api/v1/exam")
                        .param("className", "ECON 208"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].course").value("ECON 208"));
    }

    @Test
    public void testGetNonExistentExam() throws Exception {
        mockMvc.perform(get("/api/v1/exam")
                        .param("className", "NON_EXISTENT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }
}
