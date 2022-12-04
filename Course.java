class Course {
    private String course;
    private int creditHours;
    private String prerequisite;
    private String corequisite;

    public String getCourse() { return course;}
    public int getCreditHours() { return creditHours;}
    public String getPrerequisite() {return prerequisite;}
    public String getCorequisite() {return corequisite;}
    
    Course(String course,int creditHours ,String prerequisite, String corequisite) {
        this.course = course;
        this.creditHours = creditHours;
        this.prerequisite = prerequisite;
        this.corequisite = corequisite; 
    }
}


