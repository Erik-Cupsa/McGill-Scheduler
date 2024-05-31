package com.mcgill.examscheduler.exam.model;

import java.io.Serializable;
import java.util.Objects;

public class HistoricExamKey implements Serializable {
    private String course;
    private String section;
    private String year;

    public HistoricExamKey() {

    }

    public HistoricExamKey(String course, String section, String year) {
        this.course = course;
        this.section = section;
        this.year = year;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricExamKey that = (HistoricExamKey) o;
        return Objects.equals(course, that.course) && Objects.equals(section, that.section) && Objects.equals(year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, section, year);
    }
}
