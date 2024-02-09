package com.mcgill.examscheduler.exam;
import java.io.Serializable;
import java.util.Objects;

public class ExamKey implements Serializable {
    private String course;
    private String section;

    // Constructors
    public ExamKey() {
    }

    public ExamKey(String course, String section) {
        this.course = course;
        this.section = section;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamKey examKey = (ExamKey) o;
        return Objects.equals(course, examKey.course) &&
                Objects.equals(section, examKey.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, section);
    }
}
