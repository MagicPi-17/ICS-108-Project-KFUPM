public class Section {
    Course course;
    Timing time;
    String location;

    Section(Course course, Timing time, String location) {
        this.course = course;
        this.time = time;
        this.location = location;
    }

    public Course getCourse() {
        return this.course;
    }
    public Timing getTime() {
        return this.time;
    }
    public String getLocation() {
        return this.location;
    }

}


class Timing {
    int start;
    int end;
    String days;

    Timing(int start, int end, String days) {
        this.start = start;
        this.end = end;
        this.days = days;
    }

    public int getStart() {
        return this.start;
    }
    public int getEnd() {
        return this.end;
    }
    public String getDays() {
        return this.days;
    }
}