package com.example.projectgui;

import classes.Course;
import classes.Section;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Scene2 extends Application {
    ArrayList<Section> basket;
    ArrayList<Section> schedule;
    Button[][] buttons;
    Section[][] sections;

    Label[][] labels;
    GridPane leftBox;
    ScrollPane rightBox;

    VBox basketBox;
    public void start(Stage stage) {
        BorderPane bp = new BorderPane();
        leftBox = new GridPane();
        rightBox = new ScrollPane();
        HBox bottomBox = new HBox();
        bp.setLeft(leftBox);
        bp.setRight(rightBox);
        bp.setBottom(bottomBox);
        bottomBox.setAlignment(Pos.BASELINE_LEFT);
        bottomBox.setPadding(new Insets(30,30,30,30));

        Button save = new Button("Save Schedule");
        save.setPrefHeight(50);
        save.setPrefWidth(150);
        bottomBox.getChildren().add(save);


        schedule = new ArrayList<>();

        // sample basket for testing
        basket = new ArrayList<>();
        basket.add(new Section(new Course("Phys 101", "General Physics 1"),8,"0700-0750","UTR","63"));
        basket.add(new Section(new Course("Phys 102", "General Physics 2"),9,"0700-0750","UTR","63"));
        basket.add(new Section(new Course("Chem 101", "Principles of Chemistry 1"),10,"0900-0950","UTR","63"));
        basket.add(new Section(new Course("Chem 101 Lab", "Principles of Chemistry 1 Laboratory"),32,"1400-1640","M","63"));
        basket.add(new Section(new Course("Math 101", "Calculus I"),10,"0900-1015","MW","63"));
        basket.add(new Section(new Course("Math 102", "Calculus II"),11,"1500-1550","UTR","63"));
        basket.add(new Section(new Course("Math 201", "Calculus III"),12,"1200-1250","UTR","63"));
        basket.add(new Section(new Course("ICS 104", "Python programming"),5,"0700-0750","UTR","63"));
        basket.add(new Section(new Course("ICS 104 Lab", "Python Lab"),21,"0700-0940","W","63"));
        basket.add(new Section(new Course("ICS 108", "object oriented programming"),5,"1400-1640","MW","63"));
        basket.add(new Section(new Course("IAS 121", "Language Foundation"),13,"1300-1350","UT","63"));

        rightBox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))); // draw borders around rightBox

        rightBox.setPrefWidth(200);
        basketBox = new VBox();
        basketBox.setAlignment(Pos.CENTER);
        basketBox.setSpacing(10);

        // add sections in basket to basketBox and initialize buttons for addition to schedule
        for (int i = 0; i < basket.size(); i++) {
            Section sec = basket.get(i);
            Button but = new Button();
            but.setAlignment(Pos.CENTER);
            String line1 = sec.getCourse().getCourseCode() + " - " + sec.getSectionNumber();
            String line2 = sec.getDays();
            String time = sec.getTime(); // HHMM-HHMM
            String line3 = time.substring(0,2) + ":" + time.substring(2,4) + " - " + time.substring(5,7) + ":" + time.substring(7);
            but.setText(line1 + "\n" + line2 + "\n" + line3);

            EventHandler e = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    int ind = basketBox.getChildren().indexOf(e.getSource());
                    System.out.println(sec.getCourse().getName());
                    if (addSection(sec)) { // if sec is added successfully execute the if-block
                        schedule.add(sec);
                        basketBox.getChildren().get(ind).setDisable(true);
                    }
                }

            };

            but.setOnAction(e);

            but.setPrefWidth(175);
            but.setPrefHeight(100);
            basketBox.getChildren().add(but);
        }

        rightBox.setContent(basketBox);

        leftBox.setPadding(new Insets(30,30,30,30));
        leftBox.getRowConstraints().add(new RowConstraints(30));
        leftBox.setAlignment(Pos.BASELINE_CENTER);
        leftBox.add(new Label(""),0,0);

        // add the first column containing hours
        for (int i = 7; i <= 17; i++) {
            Label l;
            if (i < 12) {
                l = new Label(i + " AM");
            }
            else if (i == 12) {
                l = new Label("12 PM");
            }
            else {
                l = new Label(i + " PM");
            }

            leftBox.add(l,0,i-6);
            GridPane.setHalignment(l, HPos.CENTER);
            GridPane.setValignment(l, VPos.CENTER);
            leftBox.getRowConstraints().add(new RowConstraints(60)); // set width of column to 60
        }

        // add the first row containing days
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        for (int i = 0; i < days.length; i++) {
            Label l = new Label(days[i]);
            leftBox.add(l,i+1,0);
            GridPane.setHalignment(l, HPos.CENTER);
            GridPane.setValignment(l, VPos.CENTER);
            leftBox.getColumnConstraints().add(new ColumnConstraints(100));
        }
        leftBox.getColumnConstraints().add(new ColumnConstraints(100)); // set length of row to 100
        leftBox.setStyle("-fx-grid-lines-visible: true"); // make grid lines visible

        // initialize buttons 2d array to save buttons that are in the schedule
        buttons = new Button[11][5];
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                Button b = new Button();
                b.setDisable(true); // if a button in the array buttons is disabled then that time slot is vacant
                buttons[i][j] = b;

            }
        }
        // initialize sections and labels to 2d arrays filled with nulls
        sections = new Section[11][5];
        labels = new Label[11][5];

        Scene scene = new Scene(bp, 1000, 800);
        stage.setScene(scene);
        stage.setTitle("Design Schedule");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public boolean addSection(Section s) {
        String day = s.getDays();
        boolean[] days = new boolean[5]; // place true in place of days where there is a lecture
        days[0] = day.contains("U");
        days[1] = day.contains("M");
        days[2] = day.contains("T");
        days[3] = day.contains("W");
        days[4] = day.contains("R");
        String[] time = s.getTime().split("-"); // [start (HHMM), finish (HHMM)]
        int startHour = new Integer(time[0]) / 100;
        int finishHour = new Integer(time[1]) / 100;
        int length = (finishHour - startHour) + 1; // duration of lecture in hours (1, 2, or 3)

        int threshold = day.length();
        int counter = 0;
        for (int i = 0; i < days.length; i++) {
            // if there is a lecture that day enter the if-block
            if (days[i]) {
                // check one cell is vacant
                if (length == 1 && buttons[startHour-7][0].isDisable()) {
                    counter++;
                }
                // check two adjacent cells are vacant
                else if (length == 2 && buttons[startHour-7][0].isDisable() && buttons[startHour-6][0].isDisable()) {
                    counter++;
                }
                // check three adjacent cells are vacant
                else if (length == 3 && buttons[startHour-7][0].isDisable() && buttons[startHour-6][0].isDisable() &&
                        buttons[startHour-5][0].isDisable()) {
                    counter++;

                }
            }
        }

        if (counter == threshold) { // if there is space for all meeting times in schedule add section and return true

            for (int i = 0; i < days.length; i++) {
                if (days[i]) {
                    for (int j = 0; j < length; j++) {
                        VBox vb = new VBox();
                        vb.setAlignment(Pos.CENTER);
                        vb.setSpacing(10);
                        Label l = new Label(s.getCourse().getCourseCode());
                        vb.getChildren().add(l);
                        Button d = new Button("Del");
                        d.setPrefWidth(40);
                        d.setPrefHeight(20);
                        d.setOnAction(new Handler());
                        vb.getChildren().add(d);
                        leftBox.add(vb, i + 1, startHour - 6 + j);

                        buttons[startHour -7 + j][i] = d;
                        buttons[startHour -7 + j][i].setDisable(false); // indicate the time is occupied
                        sections[startHour -7 +j][i] = s;
                        labels[startHour -7 + j][i] = l;
                    }
                }
            }
            return true;
        }
        else { // if there is no space in schedule for all meeting times return false
            return false;
        }
    }
    class Handler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Section s = null;
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    if (e.getSource() == buttons[i][j]) {
                        s = sections[i][j];
                        System.out.println(i + " " + j);
                        break;
                    }
                }
            }
            if (s != null) {
                System.out.println(s.getCourse().getName());
                String day = s.getDays();
                boolean[] days = new boolean[5];
                days[0] = day.contains("U");
                days[1] = day.contains("M");
                days[2] = day.contains("T");
                days[3] = day.contains("W");
                days[4] = day.contains("R");
                String[] time = s.getTime().split("-");
                int startHour = new Integer(time[0]) / 100;
                int finishHour = new Integer(time[1]) / 100;
                int length = (finishHour - startHour) + 1;
                for (int i = 0; i < days.length; i++) {
                    if (days[i]) {
                        for (int j = 0; j < length; j++) {
                            VBox v = new VBox();
                            v.getChildren().addAll(buttons[startHour - 7 + j][i], labels[startHour - 7 + j][i]);
                            leftBox.getChildren().removeAll(v);
                            // buttons -> disabled button   sections, labels -> null    remove section from arrList
                            Button def = new Button();
                            def.setDisable(true);
                            buttons[startHour -7 + j][i] = def;
                            sections[startHour -7 + j][i] = null;
                            labels[startHour -7 + j][i] = null;
                        }
                    }
                }
                int ind = basket.indexOf(s);
                basketBox.getChildren().get(ind).setDisable(false);
                schedule.remove(s);
            }
        }
    }
}
