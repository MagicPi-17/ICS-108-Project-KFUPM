package com.example.termproject.classes;
import java.util.ArrayList;
import java.util.Collections;

public class Student {
    private ArrayList<FinishedCourse> finishedCourses;

    public ArrayList<FinishedCourse> getFinishedCourses() { return finishedCourses;}

    public ArrayList<Integer> getTermsSorted () {
        ArrayList<Integer> terms = new ArrayList<Integer>();

        finishedCourses.forEach(finishedCourse -> {
            int term = Integer.parseInt(finishedCourse.getTerm());
            if (Collections.binarySearch(terms, term) < 0) {
                terms.add(term);
            }
        });

        Collections.sort(terms);
        return terms;
    }
    public String getStringFinishedCourse(int index) { return finishedCourses.get(index).toString();}
    public int getTermOfCourse(int index) { return Integer.parseInt(finishedCourses.get(index).getTerm());}

    public void setFinishedCourses(ArrayList<FinishedCourse> finishedCourses) { this.finishedCourses = finishedCourses;}

    public void addFinishedCourse(String course, String term, String grade) {
        this.finishedCourses.add(new FinishedCourse(course, term, grade));
    }
    public void addFinishedCourse(String[] data) {
        this.finishedCourses.add(new FinishedCourse(data[0], data[1], data[2]));
    }

    public void removeFinishedCourse(String course, String term, String grade) {
        this.finishedCourses.remove(new FinishedCourse(course, term, grade));
    }
    public void removeFinishedCourse(int index) {
        this.finishedCourses.remove(index);
    }

    public Student() {
        this.finishedCourses = new ArrayList<FinishedCourse>();
    }
    Student(ArrayList<FinishedCourse> finishedCourses) {
        this.finishedCourses = finishedCourses;
    }

    class FinishedCourse {
        private String course;
        private String term;
        private String grade;

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
}

