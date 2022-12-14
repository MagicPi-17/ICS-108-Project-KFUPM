package com.example.termproject.classes;
import java.io.Serializable;

public class Section implements Comparable<Section>, Serializable {
    String course_section;
    String activity;
    String CRN;
    String courseName;
    String instructor;
    String days;
    String time;
    String location;
    String status;
    String waitlist;
    Double[] color;

    public Section(String course_section, String time) {
        this(course_section, "none", "none","none","none","none",time,"none","none","none");
    }

    public Section(String course_section, String Activity, String CRN,
                   String courseName, String Instructor, String day,
                   String time, String Location, String status, String waitlist){
        this.course_section = course_section;
        this.activity = Activity;
        this.CRN = CRN;
        this.courseName = courseName;
        this.instructor = Instructor;
        this.days = day;
        this.time = time;
        this.location = Location;
        this.status = status;
        this.waitlist = waitlist;
        this.color = new Double[]{Math.random() * 0.7, Math.random() * 0.7, Math.random() * 0.7, Math.random() * 0.55};
    }

    // this function return the time between two section and if they two time wrong the function return -1
    public int getTimeDifference(Section section) {
        String[] times2 = time.split("-");
        String[] times1 = section.getTime().split("-");

        int startTime1 = Integer.parseInt(times1[0]);
        int startTime2 = Integer.parseInt(times2[0]);
        int endTime1 = Integer.parseInt(times1[1]);
        int endTime2 = Integer.parseInt(times2[1]);

        int hoursBigGap = (endTime2/100) - (startTime1/100);
        int hoursSmallGap = (startTime2/100) - (endTime1/100);
        int minutesBigGap = (endTime2 % 100) - (startTime1 % 100);
        int minutesSmallGap = (startTime2 % 100) - (endTime1 % 100);

        int dif1 = hoursBigGap * 60 + minutesBigGap;
        int dif2 = hoursSmallGap * 60 + minutesSmallGap;
        // if both difference have the same sign this mean that there is no overlap
        int sameSignCheck = Integer.signum(dif1) * Integer.signum(dif2);
        if(sameSignCheck == 1 || sameSignCheck == 0) {
            return Math.min(Math.abs(dif1), Math.abs(dif2));
        }
        else {return -1;}
    }

    // return section time duration
    public double getTimeDuration() {
        String[] times = time.split("-");

        int startTime = Integer.parseInt(times[0]);
        int endTime = Integer.parseInt(times[1]);
        int hoursDiff = (endTime/100) - (startTime/100);
        int minutesDiff = (endTime % 100) - (startTime % 100);
        return hoursDiff * 60 + minutesDiff;
    }


    public Double[] getColor() {return color;}

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

    public String getDays() {
        return days;
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

    public String getScheduleText() {
        return course_section+ " " + days + "\n" + time;
    }

    @Override
    public String toString() {
        return "Section{" +
                "course_section='" + course_section + '\'' +
                ", Activity='" + activity + '\'' +
                ", CRN='" + CRN + '\'' +
                ", courseName='" + courseName + '\'' +
                ", Instructor='" + instructor + '\'' +
                ", day='" + days + '\'' +
                ", time='" + time + '\'' +
                ", Location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", waitlist='" + waitlist + '\'' +
                '}';
    }

    public Section(String[] data) {
        this(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]);
    }
    // compare to sort the sections by time
    @Override
    public int compareTo(Section o) {
        int time1 = Integer.parseInt(time.split("-")[0]);
        int time2 = Integer.parseInt(o.getTime().split("-")[0]);
        return Integer.compare(time1, time2);
    }
}

