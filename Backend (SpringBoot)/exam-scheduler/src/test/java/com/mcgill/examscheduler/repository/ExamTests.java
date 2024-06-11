package com.mcgill.examscheduler.repository;

import com.mcgill.examscheduler.exam.model.Exam;
import com.mcgill.examscheduler.exam.repo.ExamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ExamTests {

    @Autowired
    private ExamRepository examRepository;

    @Test
    public void testDatabaseConnection() {
        assertDoesNotThrow(() -> {
            // Perform a simple database operation
            long count = examRepository.count();
            System.out.println("Number of exams in the database: " + count);
        });
    }

    @Test
    public void testGetExam() {
        String course = "ECSE 200";
        String section = "1";

        Optional<Exam> optionalExam = examRepository.findByCourseAndSection(course, section);
        assertTrue(optionalExam.isPresent(), "Exam should be present in the database.");

        Exam exam = optionalExam.get();
        assertEquals(course, exam.getCourse());
        assertEquals(section, exam.getSection());
        assertEquals("Electric Circuits 1", exam.getcourse_title());
        assertEquals("IN-PERSON - FORMAL EXAM - D.T. CAMPUS", exam.getexam_type());
        assertEquals(LocalDateTime.of(2024, 4, 19, 10, 0), exam.getexam_start_time());
        assertEquals(LocalDateTime.of(2024, 4, 19, 13, 0), exam.getexam_end_time());
        assertEquals("GYM", exam.getBuilding());
        assertEquals("FIELD HOUSE", exam.getRoom());
        assertEquals("13-15", exam.getRows());
        assertEquals("AAA", exam.getRowStart());
        assertEquals("ZZZ", exam.getRowEnd());
    }
}
