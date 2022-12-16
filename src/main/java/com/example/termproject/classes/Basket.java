package com.example.termproject.classes;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class Basket {
    private final ArrayList<Section> sections = new ArrayList<>();

    public void addSection(Section section, int indexOfButton, ArrayList<Button> removeButtons, ArrayList<Button> addButtons, ArrayList<String> clickedButtonsIDs) { //adding the section disables the add button and enables the remove button
        sections.add(section);
        removeButtons.get(indexOfButton).setDisable(false);
        addButtons.get(indexOfButton).setDisable(true);
        clickedButtonsIDs.add(Integer.toString(indexOfButton));
    }

    public void removeSection(Section section, int indexOfButton, ArrayList<Button> removeButtons, ArrayList<Button> addButtons, ArrayList<String> clickedButtonsIDs) { //removing the section enables the add button and disables the remove button
        for (int i = 0; i<sections.size(); i++) {
            if (section.getCRN().equals(sections.get(i).getCRN())) {
                sections.remove(sections.get(i));
            }
        }
        removeButtons.get(indexOfButton).setDisable(true);
        addButtons.get(indexOfButton).setDisable(false);
        clickedButtonsIDs.remove(Integer.toString(indexOfButton));
    }


    public ArrayList<Section> getSections() {
        return this.sections;
    }
}