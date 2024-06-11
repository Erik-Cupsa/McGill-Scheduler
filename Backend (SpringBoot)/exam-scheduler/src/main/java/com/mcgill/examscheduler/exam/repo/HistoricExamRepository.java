package com.mcgill.examscheduler.exam.repo;

import com.mcgill.examscheduler.exam.model.HistoricExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoricExamRepository extends JpaRepository<HistoricExam, String> {
    Optional<HistoricExam> findByCourseAndSectionAndYear(String course, String section, String year);
    Optional<HistoricExam> findByCourseAndYear(String course, String year);
}
