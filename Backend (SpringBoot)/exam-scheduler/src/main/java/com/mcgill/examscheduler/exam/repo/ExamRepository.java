package com.mcgill.examscheduler.exam.repo;

import com.mcgill.examscheduler.exam.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, String> {
    Optional<Exam> findByCourseAndSection(String course, String section);

    void deleteByCourseAndSection(String course, String section);
}
