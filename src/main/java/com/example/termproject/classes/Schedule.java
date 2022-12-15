package com.example.termproject.classes;
import com.example.termproject.dataHandlerClasses.DataHandler;

import java.io.FileNotFoundException;
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

    public static void main(String[] args) throws FileNotFoundException {
        Schedule schedule = new Schedule("221");
        System.out.println(DataHandler.getAllowedSections().get(3).toString());
        System.out.println(DataHandler.getAllowedSections().get(1).toString());

        schedule.addSectionToDays(DataHandler.getAllowedSections().get(1));
        schedule.addSectionToDays(DataHandler.getAllowedSections().get(3));

        for(Section section : schedule.getSections().keySet()) {
            System.out.println("deubg " + section);
        }



    }
    TreeMap<Section, String> sections;
    String term;

    public Schedule(String term) {
        sections = new TreeMap<>(new SectionComparator());
        this.term = term;}


    public String getTerm() {return term;}

    public TreeMap<Section, String> getSections() {return sections;}



    public ArrayList<Section>[] getScheduleByDays() {
        ArrayList<Section>[] sectionsByDays = new ArrayList[5];
        ArrayList<Section> sundaySections = new ArrayList<>();
        ArrayList<Section> mondaySections = new ArrayList<>();
        ArrayList<Section> tuesSections = new ArrayList<>();
        ArrayList<Section> wednesdaySections = new ArrayList<>();
        ArrayList<Section> thursSections = new ArrayList<>();
        for(Section section : sections.keySet()) {
            for(Character day : section.getDays().toCharArray()) {
                if(day.equals('U')) sundaySections.add(section);
                else if(day.equals('M')) mondaySections.add(section);
                else if(day.equals('T')) tuesSections.add(section);
                else if(day.equals('W')) wednesdaySections.add(section);
                else if(day.equals('R')) thursSections.add(section);
            }
        }
        sectionsByDays[0] = sundaySections;
        sectionsByDays[1] = mondaySections;
        sectionsByDays[2] = tuesSections;
        sectionsByDays[3] = wednesdaySections;
        sectionsByDays[4] = thursSections;

        return  sectionsByDays;
    }

    public boolean addSectionToDays(Section section) {
        System.out.println(checkTime(section));
        if (checkTime(section)) {
            sections.put(section, section.getDays());
            return true;
        }
        else {
            return false;
        }
    }

    public void removeSection(Section section) {
        sections.remove(section);
    }
    // this function check if the section can be added the current schedule sections or not
    public Boolean checkTime(Section section) {
        char[] section1Days = section.getDays().toCharArray();
        Arrays.sort(section1Days);
        for(Section registeredSection : sections.keySet()) {
            for(Character day : registeredSection.getDays().toCharArray()) {
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



