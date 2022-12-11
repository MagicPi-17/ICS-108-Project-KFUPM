package com.example.termproject.classes;

import com.example.termproject.readWriteDataClasses.DataReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Schedule {

    public static void main(String[] args) throws FileNotFoundException {
        String[] test = {"1", "2", "3"};
        System.out.println(Arrays.binarySearch(test, "1"));
        Schedule schedule = new Schedule(DataReader.getCourseOffering(), DataReader.getStudentFinishedCourse(),"222");
        System.out.println(schedule.term);
        for (Section section : schedule.getAllowedSections()) {
            System.out.println(section.toString());
        }
    }
    ArrayList<Section> sections;
    ArrayList<Section> allowedSections = new ArrayList<Section>();
    Student student;
    String term;

    public Schedule(ArrayList<Section> sections, Student student, String term) throws FileNotFoundException {
        this.sections = sections;
        this.student = student;
        createAllowedSections();
        this.term = term;
    }

    public ArrayList<Section> getAllowedSections() {return allowedSections;}

    public void createAllowedSections() throws FileNotFoundException {
        String[] finishedCourses = student.getFinishedCoursesNames();
        Arrays.sort(finishedCourses);

        ArrayList<Course> degreePlanCourses = DataReader.getDegreePlanCourses();
        Collections.sort(degreePlanCourses);

        ArrayList<String> allowedCourses = new ArrayList<String>();

        String courseName, coursePrerequisite, courseSecName;

        for(Course course: degreePlanCourses) {
            coursePrerequisite = course.getPrerequisite();
            courseName = course.getCourse();

            if (Arrays.binarySearch(finishedCourses, courseName) < 0) {
                if (coursePrerequisite.equals("none") ||Arrays.binarySearch(finishedCourses, coursePrerequisite) >= 0){
                    allowedCourses.add(courseName);
                }
            }

        }

        Collections.sort(allowedCourses);

        for(Section section : sections) {
            courseSecName = section.getCourse_section().split("-")[0];
            if (Collections.binarySearch(allowedCourses, courseSecName) >= 0) {
                allowedSections.add(section);
                }

            }
        }


}



