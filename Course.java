class Course {
    private String prerequisite;
    private String corequisite;

    public String getPrerequisite() {return prerequisite;}
    public String getCorequisite() {return corequisite;}
    
    Course(String prerequisite, String corequisite) {
        this.prerequisite = prerequisite;
        this.corequisite = corequisite; 
    }
}


