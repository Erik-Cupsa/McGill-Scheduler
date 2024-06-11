package com.mcgill.examscheduler.integration;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@SpringBootTest
@AutoConfigureMockMvc
public class HistoricExamControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetHistoricExamsNothingProvided() throws Exception {
        mockMvc.perform(get("/api/v1/historic-exams/historic"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testGetHistoricExamsNoYear() throws Exception {
        mockMvc.perform(get("/api/v1/historic-exams/historic")
                        .param("names", "ECON 208"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testGetHistoricExamsNoName() throws Exception {
        mockMvc.perform(get("/api/v1/historic-exams/historic")
                        .param("years", "W2024"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetHistoricExamsByNamesAndYears() throws Exception {
        mockMvc.perform(get("/api/v1/historic-exams/historic")
                        .param("names", "ECON 208").param("years", "W2024"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].course").value("ECON 208"))
                .andExpect(jsonPath("$[0].year").value("W2024"));
    }

}
