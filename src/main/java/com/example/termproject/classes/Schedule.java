package com.example.termproject.classes;

import com.example.termproject.readWriteDataClasses.DataReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


class SectionComparator implements Comparator<Section> {
    @Override
    public int compare(Section o1, Section o2) {
        int time1 = Integer.parseInt(o1.getTime().split("-")[0]);
        int time2 = Integer.parseInt(o2.getTime().split("-")[0]);
        if(time1 > time2) {return 1;}
        else if(time1 == time2) {return 0;}
        else {return -1;}
    }
}

public class Schedule {

    public static void main(String[] args) throws FileNotFoundException {
        new Schedule(DataReader.getAllowedSections(), "221");

    }
    ArrayList<Section> sections;
    TreeMap<Section, String> sectionsDays = new TreeMap<>(new SectionComparator());
    ArrayList<Section> sundaySections = new ArrayList<Section>();
    ArrayList<Section> MondaySections = new ArrayList<Section>();
    ArrayList<Section> TuesSections = new ArrayList<Section>();
    ArrayList<Section> wednesdaySections = new ArrayList<Section>();
    ArrayList<Section> ThursdaySections = new ArrayList<Section>();
    String term;

    public Schedule() {
        sections = new ArrayList<Section>();
        term = "";}

    public void addSectionToDays(Section section) {
        String days = section.getDay();
        Character day;
        for(int i = 0; i < days.length();i++) {
            day = days.charAt(i);
            if(day == 'U') {}
            else if(day == 'M') {}
            else if(day == 'T') {}
            else if(day == 'W') {}
            else if(day == 'R') {}

        }
    }
    public Boolean checkTime(Section section) {
        sectionsDays.put(sections.get(0), sections.get(0).getDay());
        sectionsDays.put(sections.get(1), sections.get(1).getDay());
        sectionsDays.put(sections.get(2), sections.get(2).getDay());

        for(Section s : sectionsDays.keySet()) {
            System.out.println(section.toString());
        }



        return true;
    }

    public ArrayList<Section> getSections() {return sections;}
    public String getTerm() {return term;}
    public void setSections(ArrayList<Section> sections) {this.sections = sections;}
    public void setTerm(String term) {this.term = term;}

    public Schedule(ArrayList<Section> sections_basket, String term)  {
        this.sections = sections_basket;
        this.term = term;
    }




}



