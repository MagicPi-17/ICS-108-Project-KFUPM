package com.example.termproject.classes;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class Basket {
    private ArrayList<Section> sections = new ArrayList<>();

    public void addSection(Section section, int indexOfButton, ArrayList<Button> removeButtons, ArrayList<Button> addButtons) { //adding the section disables the add button and enables the remove button
        sections.add(section);
        removeButtons.get(indexOfButton).setDisable(false);
        addButtons.get(indexOfButton).setDisable(true);
    }

    public void removeSection(Section section, int indexOfButton, ArrayList<Button> removeButtons, ArrayList<Button> addButtons) { //removing the section enables the add button and disables the remove button
        sections.remove(section);
        removeButtons.get(indexOfButton).setDisable(true);
        addButtons.get(indexOfButton).setDisable(false);
    }

    public void clearBasket(ArrayList<Button> removeButtons, ArrayList<Button> addButtons) {
        sections.clear();
        removeButtons.clear();
        addButtons.clear();
    }


    public ArrayList<Section> getSections() {
        return this.sections;
    }
}