package com.example.termproject.classes;

import com.example.termproject.readWriteDataClasses.DataReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Schedule {
    ArrayList<Section> sections_basket;
    String term;

    public Schedule() {
        sections_basket = new ArrayList<Section>();
        term = "";}

    public ArrayList<Section> getSections_basket() {return sections_basket;}
    public String getTerm() {return term;}
    public void setSections_basket(ArrayList<Section> sections_basket) {this.sections_basket = sections_basket;}
    public void setTerm(String term) {this.term = term;}

    public Schedule(ArrayList<Section> sections_basket, String term)  {
        this.sections_basket = sections_basket;
        this.term = term;
    }




}



