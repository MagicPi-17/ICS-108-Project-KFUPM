package com.example.termproject.readWriteDataClasses;

import com.example.termproject.classes.CSVReader;
import com.example.termproject.classes.Course;
import com.example.termproject.classes.Section;
import com.example.termproject.classes.Student;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DataReader {

    public static Student getStudentFinishedCourse() throws FileNotFoundException {
        Student newStudent = new Student();
        CSVReader cvsReader = new CSVReader("src/main/java/com/example/termproject/data/FinishedCourses.csv");
        for (String[] finishedCourseData : cvsReader.readTo2DArray()) {
            newStudent.addFinishedCourse(finishedCourseData);
            }
        return newStudent;
        }

    public static ArrayList<Section> getCourseOffering() throws FileNotFoundException {
        ArrayList<Section> sections = new ArrayList<Section>();
        CSVReader cvsReader = new CSVReader("src/main/java/com/example/termproject/data/CourseOffering.csv");
        for (String[] section : cvsReader.readTo2DArray()) {
            sections.add(new Section(section));
        }
        sections.remove(0);
        return sections;
    }

    public static ArrayList<Course> getDegreePlanCourses() throws FileNotFoundException {
        ArrayList<Course> degreePlanCourses = new ArrayList<Course>();
        CSVReader cvsReader = new CSVReader("src/main/java/com/example/termproject/data/DegreePlan.csv");
        for (String[] course : cvsReader.readTo2DArray()) {
            degreePlanCourses.add(new Course(course));
        }
        degreePlanCourses.remove(0);
        return degreePlanCourses;
    }


    public static void main(String[] args) {
        try {
            ArrayList<Section> sections = getCourseOffering();
            System.out.println(sections.get(0).toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
