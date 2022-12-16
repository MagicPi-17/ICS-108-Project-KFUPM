package com.example.termproject.classes;

public class FinishedCourse {
    private final String course;
    private final String term;
    private final String grade;

    public String getCourse() {
        return course;
    }

    public String getTerm() {
        return term;
    }

    public String getGrade() {
        return grade;
    }

    FinishedCourse(String course, String term, String grade) {
        this.course = course;
        this.term = term;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("%-8s %2s" , course, grade);
    }
}
