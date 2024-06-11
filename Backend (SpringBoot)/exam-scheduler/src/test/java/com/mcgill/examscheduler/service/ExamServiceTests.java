package com.mcgill.examscheduler.service;

import com.mcgill.examscheduler.exam.model.Exam;
import com.mcgill.examscheduler.exam.model.ExamKey;
import com.mcgill.examscheduler.exam.repo.ExamRepository;
import com.mcgill.examscheduler.exam.service.ExamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTests {
    @Mock
    private ExamRepository examRepository;
    @InjectMocks
    ExamService examService;

    @Test
    public void testGetExams() {
        Exam exam1 = new Exam();
        exam1.setCourse("Course1");

        Exam exam2 = new Exam();
        exam2.setCourse(("Course2"));

        when(examRepository.findAll()).thenReturn(Arrays.asList(exam1, exam2));

        List<Exam> exams = examService.getExams();
        assertEquals(2, exams.size());
        verify(examRepository, times(1)).findAll();
    }
    @Test
    public void testGetExamsByClass() {
        String className = "ECON 208";
        Exam exam = new Exam();
        exam.setCourse(className);

        when(examRepository.findAll()).thenReturn(Arrays.asList(exam));

        List<Exam> result = examService.getExamsByClass(className);
        assertEquals(1, result.size());
        assertEquals(className, result.get(0).getCourse());

        verify(examRepository, times(1)).findAll();
    }

    @Test
    public void testAddExam() {
        Exam exam = new Exam();
        when(examRepository.save(exam)).thenReturn(exam);

        Exam savedExam = examService.addExam(exam);
        assertEquals(exam, savedExam);

        verify(examRepository, times(1)).save(exam);
    }

    @Test
    public void testUpdateExam() {
        ExamKey examKey = new ExamKey("Course1", "001");
        Exam existingExam = new Exam();
        existingExam.setCourse("Course1");
        existingExam.setSection("001");

        Exam updatedExam = new Exam();
        updatedExam.setCourse("Course1");
        updatedExam.setSection("001");
        updatedExam.setcourse_title("Updated Title");

        when(examRepository.findByCourseAndSection("Course1", "001")).thenReturn(Optional.of(existingExam));
        when(examRepository.save(existingExam)).thenReturn(existingExam);

        Exam result = examService.updateExam(examKey, updatedExam);
        assertNotNull(result);
        assertEquals("Updated Title", result.getcourse_title());

        verify(examRepository, times(1)).findByCourseAndSection("Course1", "001");
        verify(examRepository, times(1)).save(existingExam);
    }

    @Test
    public void testDeleteExam() {
        ExamKey examKey = new ExamKey("Course1", "001");

        Exam exam = new Exam();
        exam.setCourse("Course1");
        exam.setSection("001");

        when(examRepository.findByCourseAndSection("Course1", "001")).thenReturn(Optional.of(exam));
        doNothing().when(examRepository).deleteByCourseAndSection("Course1", "001");

        examService.deleteExam(examKey);

        verify(examRepository, times(1)).findByCourseAndSection("Course1", "001");
        verify(examRepository, times(1)).deleteByCourseAndSection("Course1", "001");
    }

    @Test
    public void testGetExamsByNames() {
        List<String> examNames = Arrays.asList("Course1", "Course2");

        Exam exam1 = new Exam();
        exam1.setCourse("Course1");

        Exam exam2 = new Exam();
        exam2.setCourse("Course2");

        when(examRepository.findAll()).thenReturn(Arrays.asList(exam1, exam2));

        List<Exam> result = examService.getExamsByNames(examNames);

        assertEquals(2, result.size());
        assertEquals("Course1", result.get(0).getCourse());
        assertEquals("Course2", result.get(1).getCourse());

        verify(examRepository, times(1)).findAll();
    }
}