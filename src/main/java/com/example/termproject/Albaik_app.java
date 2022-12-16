package com.example.termproject;

import com.example.termproject.Scenes.FirstScene;
import com.example.termproject.Scenes.SecondScene;
import com.example.termproject.classes.Basket;
import com.example.termproject.classes.Schedule;
import com.example.termproject.classes.Section;
import com.example.termproject.dataHandlerClasses.DataHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Albaik_app extends Application {
    protected final  int screenWidth = 1920;
    protected final  int screenHeight = 1000;
    Schedule schedule = new Schedule("221");
    Basket basket = new Basket();
    protected Stage stage;
    protected final Button btLoadSchedule = new Button("Load saved schedule");
    protected final Button btSaveSchedule = new Button("Save schedule");
    protected final  Button btNext = new Button("Next");
    protected final Button btPrevious = new Button("Previous");
    private ArrayList<Button> addButtons = new ArrayList<>();
    private ArrayList<Button> removeButtons = new ArrayList<>();
    private ArrayList<String> clickedButtonsIDs = new ArrayList<>();
    private HashMap<String, Integer> sectionsIndexes = new HashMap<>();
    private FirstScene firstScene = new FirstScene(btNext, btLoadSchedule, basket, addButtons, removeButtons, clickedButtonsIDs, sectionsIndexes);
    private String dataFileName = "scheduleData.dat";
    private SecondScene secondScene;



    @Override // Override the start method in the Application class
    public void start(Stage stage) throws FileNotFoundException {
        this.stage = stage;
        secondScene = new SecondScene(stage,schedule, basket, btPrevious, btSaveSchedule, sectionsIndexes, addButtons,  removeButtons, clickedButtonsIDs);

        buttonsSetup(); //Gives handler to each button
        setFirstScene(); //First scene

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setFirstScene() {
        try {
            HBox courseOfferingPane = firstScene.getCourseOfferingPane("Add Sections to Basket");

            Scene scene = new Scene(courseOfferingPane, screenWidth, screenHeight);
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("Error!!\n" + e.getMessage());
        }
    }

    public void buttonsSetup() {
        ButtonHandler handler = new ButtonHandler(); //adding handlers to the buttons
        btNext.setOnAction(handler);
        btPrevious.setOnAction(handler);
        btLoadSchedule.setOnAction(handler);
        btSaveSchedule.setOnAction(handler);

        btPrevious.setFont(Font.font ("Verdana", 20));
        btPrevious.setMinHeight(100);
        btPrevious.setMinWidth(220);

        btSaveSchedule.setFont(Font.font ("Verdana", 20));
        btSaveSchedule.setMinHeight(100);
        btSaveSchedule.setMinWidth(220);
    }

    public class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == btNext) {
                stage.setScene(secondScene.getSecondScene());
            }

            else if (actionEvent.getSource() == btPrevious) {
                addButtons.clear();
                removeButtons.clear();
                setFirstScene();
                firstScene.fixAddRemoveButtons(clickedButtonsIDs);
            }
            else if (actionEvent.getSource() == btLoadSchedule) {
                try {
                    schedule = DataHandler.readSchedule(dataFileName);
                    secondScene.setSchedule(schedule);

                    Boolean check = false;
                    for(Section section : schedule.getSections()) {
                        check = false;
                        for(Section basketSection : basket.getSections()){
                            if(section.getCRN().equals(basketSection.getCRN())) {
                                check = true;
                                break;
                            }
                        }
                        if(!check) basket.addSection(section, sectionsIndexes.get(section.getCRN()), removeButtons, addButtons, clickedButtonsIDs);
                    }
                    addButtons.clear();
                    removeButtons.clear();
                    setFirstScene();
                    firstScene.fixAddRemoveButtons(clickedButtonsIDs);
                } catch (IOException e) {
                    try {
                        DataHandler.writeSchedule(dataFileName, schedule);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (actionEvent.getSource() == btSaveSchedule) {
                try {
                    DataHandler.writeSchedule(dataFileName, schedule);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

