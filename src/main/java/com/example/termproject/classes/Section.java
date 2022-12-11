package com.example.termproject.classes;


// Course-Sec,Activity,CRN,Course Name,Instructor,Day,Time,Location,Status,Waitlist
public class Section {
    String course_section;
    String activity;
    String CRN;
    String courseName;
    String instructor;
    String day;
    String time;
    String location;
    String status;
    String waitlist;

    public Section(String course_section, String Activity, String CRN,
                   String courseName, String Instructor, String day,
                   String time, String Location, String status, String waitlist){
        this.course_section = course_section;
        this.activity = Activity;
        this.CRN = CRN;
        this.courseName = courseName;
        this.instructor = Instructor;
        this.day = day;
        this.time = time;
        this.location = Location;
        this.status = status;
        this.waitlist = waitlist;
    }


    public String getActivity() {
        return activity;
    }

    public String getCourse_section() {
        return course_section;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCRN() {
        return CRN;
    }

    public String getDay() {
        return day;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getWaitlist() {
        return waitlist;
    }

    @Override
    public String toString() {
        return "Section{" +
                "course_section='" + course_section + '\'' +
                ", Activity='" + activity + '\'' +
                ", CRN='" + CRN + '\'' +
                ", courseName='" + courseName + '\'' +
                ", Instructor='" + instructor + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", Location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", waitlist='" + waitlist + '\'' +
                '}';
    }

    public Section(String[] data) {
        this(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]);
    }

}

