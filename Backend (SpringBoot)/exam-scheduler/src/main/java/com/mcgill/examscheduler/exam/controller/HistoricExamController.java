package com.mcgill.examscheduler.exam.controller;

import com.mcgill.examscheduler.exam.model.HistoricExam;
import com.mcgill.examscheduler.exam.service.HistoricExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/historic-exams")
public class HistoricExamController {
    private final HistoricExamService historicExamService;

    @Autowired
    public HistoricExamController(HistoricExamService historicExamService) {
        this.historicExamService = historicExamService;
    }

    @GetMapping("/historic")
    public List<HistoricExam> getHistoricExamsByNameAndYear(@RequestParam("names") List<String> classNames, @RequestParam("years") List<String> years) {
        return historicExamService.getExamsByNameAndYear(classNames, years);
    }
}
