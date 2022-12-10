package com.example.termproject.readWriteDataClasses;

import com.example.termproject.classes.CSVReader;
import com.example.termproject.classes.Student;

import java.io.FileNotFoundException;

public class DataReader {

    public static Student getFinishedCourse() throws FileNotFoundException {
        Student newStudent = new Student();
        CSVReader cvsReader = new CSVReader("src/main/java/com/example/termproject/data/FinishedCourses.csv");
        for (String[] finishedCourseData : cvsReader.readTo2DArray()) {
            newStudent.addFinishedCourse(finishedCourseData);
        }
        return newStudent;

    }
}
