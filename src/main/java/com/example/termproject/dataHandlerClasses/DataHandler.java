package com.example.termproject.dataHandlerClasses;

import com.example.termproject.classes.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DataHandler {

    // return student object that contain finished courses
    public static Student getStudentFinishedCourse() throws FileNotFoundException {
        Student newStudent = new Student();
        CSVReader cvsReader = new CSVReader("src/main/java/com/example/termproject/data/FinishedCourses.csv");
        for (String[] finishedCourseData : cvsReader.readTo2DArray()) {
            newStudent.addFinishedCourse(finishedCourseData);
        }
        return newStudent;
    }

    // return all course offering sections in a list
    public static ArrayList<Section> getCourseOffering() throws FileNotFoundException {
        ArrayList<Section> sections = new ArrayList<>();
        CSVReader cvsReader = new CSVReader("src/main/java/com/example/termproject/data/CourseOffering.csv");
        for (String[] section : cvsReader.readTo2DArray()) {
            sections.add(new Section(section));
        }
        sections.remove(0);
        return sections;
    }

    // return a list of degree plan courses
    public static ArrayList<Course> getDegreePlanCourses() throws FileNotFoundException {
        ArrayList<Course> degreePlanCourses = new ArrayList<>();
        CSVReader cvsReader = new CSVReader("src/main/java/com/example/termproject/data/DegreePlan.csv");
        for (String[] course : cvsReader.readTo2DArray()) {
            degreePlanCourses.add(new Course(course));
        }
        degreePlanCourses.remove(0);
        return degreePlanCourses;
    }

    // this method return all the sections that the student can add
    public static ArrayList<Section> getAllowedSections() throws FileNotFoundException {
        ArrayList<Section> allowedSections = new ArrayList<>();

        Student student = DataHandler.getStudentFinishedCourse();
        String[] finishedCourses = student.getFinishedCoursesNames();
        Arrays.sort(finishedCourses);

        ArrayList<Section> sections = DataHandler.getCourseOffering();

        ArrayList<Course> degreePlanCourses = DataHandler.getDegreePlanCourses();
        int studentCreditHours = 0;
        for(Course course : degreePlanCourses) {
            for(String finishedCourse : finishedCourses) {
                if(finishedCourse.equals(course.getCourse())) {
                    studentCreditHours += Integer.parseInt(course.getCreditHours());
                }
            }
        }
        Collections.sort(degreePlanCourses);

        ArrayList<String> allowedCourses = new ArrayList<>();

        String courseName, coursePrerequisites, courseSecName;
        boolean isAllowedCourse;
        int standingLevel = 0;

        for (Course course : degreePlanCourses) {
            coursePrerequisites = course.getPrerequisite();
            courseName = course.getCourse();
            isAllowedCourse = true;
            for(String coursePrerequisite : coursePrerequisites.split(";")) {
                if (coursePrerequisite.startsWith("Sophomore")) {
                    standingLevel = 31;
                } else if (coursePrerequisite.startsWith("Junior")) {
                    standingLevel = 63;
                } else if (coursePrerequisite.startsWith("Senior")) {
                    standingLevel = 97;
                }

                if (Arrays.binarySearch(finishedCourses, courseName) < 0 && studentCreditHours >= standingLevel) {
                    if (!(coursePrerequisite.equals("None") || Arrays.binarySearch(finishedCourses, coursePrerequisite) >= 0)) {
                        isAllowedCourse = false;
                    }
                }
                else {
                    isAllowedCourse = false;}
            }

            if(isAllowedCourse) allowedCourses.add(courseName);

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

    public static void writeSchedule(String fileName, Schedule schedule) throws IOException {
        File sFile = new File(fileName);

        FileOutputStream outputFile = new FileOutputStream(sFile);
        ObjectOutputStream output = new ObjectOutputStream(outputFile);

        output.writeObject(schedule);
        output.close();

    }

    public static Schedule readSchedule(String fileName) throws IOException, ClassNotFoundException {
        File sFile = new File(fileName);
        FileInputStream inputFile = new FileInputStream(sFile);
        ObjectInputStream input = new ObjectInputStream(inputFile);
        return (Schedule) input.readObject();



    }


}

