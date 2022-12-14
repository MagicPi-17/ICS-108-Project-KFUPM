package com.example.termproject.dataHandlerClasses;

import com.example.termproject.classes.CSVReader;
import com.example.termproject.classes.Course;
import com.example.termproject.classes.Section;
import com.example.termproject.classes.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DataHandler {

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

    // this method return all the sections that the student can add
    public static ArrayList<Section> getAllowedSections() throws FileNotFoundException {
        ArrayList<Section> allowedSections = new ArrayList<Section>();

        Student student = DataHandler.getStudentFinishedCourse();
        String[] finishedCourses = student.getFinishedCoursesNames();
        Arrays.sort(finishedCourses);

        ArrayList<Section> sections = DataHandler.getCourseOffering();

        ArrayList<Course> degreePlanCourses = DataHandler.getDegreePlanCourses();
        Collections.sort(degreePlanCourses);

        ArrayList<String> allowedCourses = new ArrayList<String>();

        String courseName, coursePrerequisite, courseSecName;


        for (Course course : degreePlanCourses) {
            coursePrerequisite = course.getPrerequisite();
            courseName = course.getCourse();

            if (Arrays.binarySearch(finishedCourses, courseName) < 0) {
                if (coursePrerequisite.equals("none") || Arrays.binarySearch(finishedCourses, coursePrerequisite) >= 0) {
                    allowedCourses.add(courseName);
                }
            }

        }

        Collections.sort(allowedCourses);
        for (Section section : sections) {
            courseSecName = section.getCourse_section().split("-")[0];
            if (Collections.binarySearch(allowedCourses, courseSecName) >= 0) {
                allowedSections.add(section);
            }
        }
        return  allowedSections;
    }

    public static void writeObject(String fileName, Object object) throws IOException {
        File sFile = new File(fileName);

        FileOutputStream outputFile = new FileOutputStream(sFile);
        ObjectOutputStream output = new ObjectOutputStream(outputFile);

        output.writeObject(object);
        output.close();

    }

    public static void readObject(String fileName) throws IOException, ClassNotFoundException {
        File sFile = new File(fileName);
        FileInputStream inputFile = new FileInputStream(sFile);
        ObjectInputStream input = new ObjectInputStream(inputFile);

        input.readObject();
        input.close();



    }

    public static void main(String[] args) {
        try {
            ArrayList<Section> sections = getAllowedSections();
            for(Section section : sections) {
                System.out.println(section.toString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

