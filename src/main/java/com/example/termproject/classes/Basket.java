package com.example.termproject.classes;

import java.util.ArrayList;

public class Basket {
    private ArrayList<Section> sections = new ArrayList<>();

    public void addSection(Section section) {
        sections.add(section);
    }

    public void removeSection(Section section) {
        sections.remove(section);
    }


    public ArrayList<Section> getSections() {
        return this.sections;
    }
}