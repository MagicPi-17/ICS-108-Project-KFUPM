package com.example.termproject.classes;
import java.util.ArrayList;


public class Student {
    private final ArrayList<FinishedCourse> finishedCourses;

    public Student() {
        this.finishedCourses = new ArrayList<>();

    }
    public String[] getFinishedCoursesNames(){
        String[] finishedCoursesNames = new String[finishedCourses.size()];
        for(int i = 0; i < finishedCourses.size(); i++) {
            finishedCoursesNames[i] = finishedCourses.get(i).getCourse();
        }

        return  finishedCoursesNames;
    }


    public void addFinishedCourse(String[] data) {
        this.finishedCourses.add(new FinishedCourse(data[0], data[1], data[2]));
    }


}



