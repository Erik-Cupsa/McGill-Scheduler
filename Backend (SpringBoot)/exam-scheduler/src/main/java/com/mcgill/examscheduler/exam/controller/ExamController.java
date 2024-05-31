package com.mcgill.examscheduler.exam.controller;

import com.mcgill.examscheduler.exam.service.ExamService;
import com.mcgill.examscheduler.exam.model.Exam;
import com.mcgill.examscheduler.exam.model.ExamKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/exam")
public class ExamController {
    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }


    @GetMapping
    public List<Exam> getExams(@RequestParam(required = false) String className){

        if (className != null){
            return examService.getExamsByClass(className);
        }
        else{
            return examService.getExams();
        }
    }

    @GetMapping("/multiple")
    public List<Exam> getExamsByNames(@RequestParam("names") List<String> examNames) {
        return examService.getExamsByNames(examNames);
    }

    @PostMapping
    public ResponseEntity<Exam> addExam(@RequestBody Exam newExam) {
        Exam createdExam = examService.addExam(newExam);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Exam> updateExam(@RequestParam String className, @RequestParam String section, @RequestBody Exam updatedExam) {
        // Create an ExamKey object based on the course and section
        ExamKey examKey = new ExamKey(className, section);

        // Pass the composite key and the updated exam details to the service method
        Exam resultExam = examService.updateExam(examKey, updatedExam);

        if (resultExam != null) {
            return new ResponseEntity<>(resultExam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteExamsByClassName(@RequestParam(required = false) String className, @RequestParam(required = false) String section) {
        if (className != null && section != null) {
            ExamKey examKey = new ExamKey(className, section);
            examService.deleteExam(examKey);
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
