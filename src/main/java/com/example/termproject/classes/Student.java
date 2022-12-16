package com.example.termproject.classes;
import java.util.ArrayList;
import java.util.Collections;

public class Student {
    private ArrayList<FinishedCourse> finishedCourses;
    private String standing;

    public Student() {
        this.finishedCourses = new ArrayList<>();

    }
    Student(ArrayList<FinishedCourse> finishedCourses) {
        this.finishedCourses = finishedCourses;
    }

    public ArrayList<FinishedCourse> getFinishedCourses() {
        return finishedCourses;
    }


    public String getStringFinishedCourse(int index) {
        return finishedCourses.get(index).toString();
    }

    public String[] getFinishedCoursesNames(){
        String[] finishedCoursesNames = new String[finishedCourses.size()];
        for(int i = 0; i < finishedCourses.size(); i++) {
            finishedCoursesNames[i] = finishedCourses.get(i).getCourse();
        }

        return  finishedCoursesNames;
    }

    public int getTermOfCourse(int index) {
        return Integer.parseInt(finishedCourses.get(index).getTerm());
    }

    public void setFinishedCourses(ArrayList<FinishedCourse> finishedCourses) {
        this.finishedCourses = finishedCourses;
    }

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


}



