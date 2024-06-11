package com.mcgill.examscheduler.service;

import com.mcgill.examscheduler.exam.model.HistoricExam;
import com.mcgill.examscheduler.exam.repo.HistoricExamRepository;
import com.mcgill.examscheduler.exam.service.HistoricExamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HistoricExamServiceTests {
    @Mock
    private HistoricExamRepository historicExamRepository;
    @InjectMocks
    HistoricExamService historicExamService;

    @Test
    public void testGetExamsByNameAndYear() {
        List<String> classNames = Arrays.asList("ECON 208");
        List<String> years = Arrays.asList(("W2024"));

        HistoricExam exam1 = new HistoricExam();
        exam1.setCourse("ECON 208");
        exam1.setYear("W2024");

        HistoricExam exam2 = new HistoricExam();
        exam2.setCourse("ECON 208");
        exam2.setYear("W2024");

        when(historicExamRepository.findAll()).thenReturn(Arrays.asList(exam1, exam2));

        List<HistoricExam> result = historicExamService.getExamsByNameAndYear(classNames, years);

        assertEquals(1, result.size());
        assertEquals("ECON 208", result.get(0).getCourse());
        assertEquals("W2024", result.get(0).getYear());

        verify(historicExamRepository, times(1)).findAll();
    }
}
