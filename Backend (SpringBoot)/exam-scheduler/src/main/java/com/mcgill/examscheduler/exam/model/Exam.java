package com.mcgill.examscheduler.exam.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "w2024")
@IdClass(ExamKey.class)
public class Exam implements Serializable {
    @Id
    @Column(name = "course")
    private String course;
    @Id
    @Column(name = "section")
    private String section;
    private String course_title;
    private String exam_type;
    private LocalDateTime exam_start_time;
    private LocalDateTime exam_end_time;
    private String building;
    private String room;
    private String rows_from;
    private String row_start;
    private String row_end;


    public Exam(String course, String section, String course_title, String exam_type, LocalDateTime exam_start_time, LocalDateTime exam_end_time, String building, String room, String rows_from, String row_start, String row_end) {
        this.course = course;
        this.section = section;
        this.course_title = course_title;
        this.exam_type = exam_type;
        this.exam_start_time = exam_start_time;
        this.exam_end_time = exam_end_time;
        this.building = building;
        this.room = room;
        this.rows_from = rows_from;
        this.row_start = row_start;
        this.row_end = row_end;
    }

    public Exam(String course, String section, String course_title, String exam_type, LocalDateTime exam_start_time, LocalDateTime exam_end_time, String building, String room, String row_start, String row_end) {
        this.course = course;
        this.section = section;
        this.course_title = course_title;
        this.exam_type = exam_type;
        this.exam_start_time = exam_start_time;
        this.exam_end_time = exam_end_time;
        this.building = building;
        this.room = room;
        this.rows_from = null;
        this.row_start = row_start;
        this.row_end = row_end;
    }

    public Exam() {

    }

    public String getCourse() {
        return course;
    }

    public String getSection() {
        return section;
    }

    public String getcourse_title() {
        return course_title;
    }

    public String getexam_type() {
        return exam_type;
    }

    public LocalDateTime getexam_start_time() {
        return exam_start_time;
    }

    public LocalDateTime getexam_end_time() {
        return exam_end_time;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoom() {
        return room;
    }

    public String getRows() {
        return rows_from;
    }

    public String getRowStart() {
        return row_start;
    }

    public String getRowEnd() {
        return row_end;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setcourse_title(String course_title) {
        this.course_title = course_title;
    }

    public void setexam_type(String exam_type) {
        this.exam_type = exam_type;
    }

    public void setexam_start_time(LocalDateTime exam_start_time) {
        this.exam_start_time = exam_start_time;
    }

    public void setexam_end_time(LocalDateTime exam_end_time) {
        this.exam_end_time = exam_end_time;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setRows(String rows_from) {
        this.rows_from = rows_from;
    }

    public void setRowStart(String row_start) {
        this.row_start = row_start;
    }

    public void setRowEnd(String row_end) {
        this.row_end = row_end;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "course='" + course + '\'' +
                ", section='" + section + '\'' +
                ", course_title='" + course_title + '\'' +
                ", exam_type='" + exam_type + '\'' +
                ", exam_start_time=" + exam_start_time +
                ", exam_end_time=" + exam_end_time +
                ", building='" + building + '\'' +
                ", room='" + room + '\'' +
                ", rows_from='" + rows_from + '\'' +
                ", row_start='" + row_start + '\'' +
                ", row_end='" + row_end + '\'' +
                '}';
    }

    public ExamKey getExamKey() {
        return new ExamKey(this.course, this.section);
    }

    public void setExamKey(ExamKey examKey) {
        this.course = examKey.getCourse();
        this.section = examKey.getSection();
    }
}
