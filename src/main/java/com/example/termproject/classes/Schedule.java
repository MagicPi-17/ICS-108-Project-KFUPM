package com.example.termproject.classes;
import java.util.*;


class SectionComparator implements Comparator<Section> {
    @Override
    public int compare(Section o1, Section o2) {
        int time1 = Integer.parseInt(o1.getTime().split("-")[0]);
        int time2 = Integer.parseInt(o2.getTime().split("-")[0]);
        return Integer.compare(time1, time2);
    }
}

public class Schedule {

    public static void main(String[] args) {
        Schedule schedule = new Schedule("221");


    }
    TreeMap<Section, String> sections;
    String term;

    public Schedule(String term) {
        sections = new TreeMap<>(new SectionComparator());
        this.term = term;}


    public String getTerm() {return term;}

    public TreeMap<Section, String> getSections() {return sections;}


    public boolean addSectionToDays(Section section) {
        if (checkTime(section)) {
            sections.put(section, section.getDays());
            return true;
        }
        else {
            return false;
        }
    }
    // this function check if the section can be added the current schedule sections or not
    public Boolean checkTime(Section section) {
        String[] section1Days = section.getDays().split("");
        Arrays.sort(section1Days);
        for(Section registeredSection : sections.keySet()) {
            System.out.println(registeredSection.toString());
            for(String day : registeredSection.getDays().split("")) {
                if (Arrays.binarySearch(section1Days,day) >= 0) {
                    if (section.getTimeDifference(registeredSection) == -1) {
                        return false;
                    }
                }
            }

        }
        return true;
    }








}



