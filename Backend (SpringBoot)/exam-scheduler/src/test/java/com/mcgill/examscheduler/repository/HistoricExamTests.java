package com.mcgill.examscheduler.repository;

import com.mcgill.examscheduler.exam.model.HistoricExam;
import com.mcgill.examscheduler.exam.repo.HistoricExamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HistoricExamTests {
    @Autowired
    private HistoricExamRepository historicExamRepository;

    @Test
    public void testDatabaseConnection(){
        assertDoesNotThrow(() -> {
            long count = historicExamRepository.count();
            System.out.println("Number of historic exams in the database: " + count);
        });
    }

    @Test
    public void testGetHistoricExam(){
        String course = "ECON 208";
        String year = "W2024";
        String exam_type = "IN-PERSON - FORMAL EXAM - D.T. CAMPUS";
        String exam_start_time = "25-Apr-2024 at 9:00 AM";
        String exam_end_time = "25-Apr-2024 at 12:00 PM";
        String building = "GYM";
        String room = "MAIN GYM";
        String rows = "14-33";
        String row_start = "AAA";
        String row_end = "ZZZ";

        Optional<HistoricExam> optionalHistoricExam = historicExamRepository.findByCourseAndYear(course, year);
        assertTrue(optionalHistoricExam.isPresent(), "Historic Exam should be present in the database");

        HistoricExam historicExam = optionalHistoricExam.get();
        assertEquals(course, historicExam.getCourse());
        assertEquals(year, historicExam.getYear());
        assertEquals(exam_type, historicExam.getExam_type());
        assertEquals(exam_start_time, historicExam.getExam_start_time());
        assertEquals(exam_end_time, historicExam.getExam_end_time());
        assertEquals(building, historicExam.getBuilding());
        assertEquals(room, historicExam.getRoom());
        assertEquals(rows, historicExam.getRows_from());
        assertEquals(row_start, historicExam.getRow_start());
        assertEquals(row_end, historicExam.getRow_end());
    }
}
