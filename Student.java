import java.util.ArrayList;

class Student {
    private ArrayList<FinishedCourse> finishedCourses;
    public ArrayList<FinishedCourse> getFinishedCourses() { return finishedCourses;}
    Student() {
        this.finishedCourses = new ArrayList<FinishedCourse>();
    }
    Student(ArrayList<FinishedCourse> finishedCourses) {
        this.finishedCourses = finishedCourses;
    }
}

class FinishedCourse {
    private String course;
    private String term;
    private String grade;

    public String getCourse() { return course;}
    public String getTerm() { return term;}
    public String getGrade() { return grade;}

    FinishedCourse(String course, String term, String grade){
        this.course = course;
        this.term = term;
        this.grade = grade;
    }
}