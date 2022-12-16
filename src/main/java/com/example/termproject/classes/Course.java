package com.example.termproject.classes;

public class Course implements Comparable<Course>{
    private final String course;
    private final String creditHours;
    private final String prerequisite;
    private final String corequisite;

    public String getCourse() { return course;}
    public String getCreditHours() { return creditHours;}
    public String getPrerequisite() {return prerequisite;}
    public String getCorequisite() {return corequisite;}
    
    public Course(String course,String creditHours ,String prerequisite, String corequisite) {
        this.course = course;
        this.creditHours = creditHours;
        this.prerequisite = prerequisite;
        this.corequisite = corequisite; 
    }
    public Course(String[] data) {
        this(data[0], data[1], data[2], data[3]);
    }

    @Override
    public int compareTo(Course o) {
        return course.compareTo(o.getCourse());
    }
}



