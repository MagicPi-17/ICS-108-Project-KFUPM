package com.example.termproject.classes;


import java.util.Arrays;
import java.util.Comparator;

// Course-Sec,Activity,CRN,Course Name,Instructor,Day,Time,Location,Status,Waitlist



public class Section implements Comparable<Section> {
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

    public static void main(String[] args) {
        String[] strings1 = "AE  540-01,LEC,23978,Flight Dynamics and Control I,Ayman Abdallah,UT,0800-0900,63-024,Open,Closed".split(",");
        Section section = new Section(strings1);
        String[] strings2 = "AE  540-01,LEC,23978,Flight Dynamics and Control I,Ayman Abdallah,UT,0730-0830,63-024,Open,Closed".split(",");
        Section section1 = new Section(strings2);

        int result = section1.getTimeDifference(section);
        System.out.println(result);
    }


    // this function return the time between two section and if they two time wrong the function return -1
    public int getTimeDifference(Section section) {
        String[] times2 = time.split("-");
        String[] times1 = section.getTime().split("-");

        int startTime1 = Integer.valueOf(times1[0]);
        int startTime2 = Integer.valueOf(times2[0]);
        int endTime1 = Integer.valueOf(times1[1]);
        int endTime2 = Integer.valueOf(times2[1]);

        int hoursBigGap = (endTime2/100) - (startTime1/100) - 1;
        int hoursSmallGap = (startTime2/100) - (endTime1/100) - 1;
        int minutesBigGap = 60 + (endTime2 % 100) - (startTime1 % 100);
        int minutesSmallGap = 60 + (startTime2 % 100) - (endTime1 % 100);

        int dif1 = hoursBigGap * 60 + minutesBigGap;
        int dif2 = hoursSmallGap * 60 + minutesSmallGap;

        int sameSignCheck = Integer.signum(dif1) * Integer.signum(dif2);
        if(sameSignCheck == 1 || sameSignCheck == 0) {
            return (Math.abs(dif1) > Math.abs(dif2)) ? Math.abs(dif2): Math.abs(dif1);
        }
        else {return -1;}
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

    @Override
    public int compareTo(Section o) {
        int time1 = Integer.parseInt(time.split("-")[0]);
        int time2 = Integer.parseInt(o.getTime().split("-")[0]);
        if(time1 > time2) {return 1;}
        else if(time1 == time2) {return 0;}
        else {return -1;}
    }
}

