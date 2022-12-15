package com.example.termproject;

import com.example.termproject.Scenes.SecondScene;
import com.example.termproject.classes.Basket;
import com.example.termproject.classes.Schedule;
import com.example.termproject.dataHandlerClasses.DataHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Albaik_app extends Application {
    protected final Button btLoadSchedule = new Button("Load saved schedule");
    protected final Button btSaveSchedule = new Button("Save schedule");
    protected final  Button btNext = new Button("Next");
    protected final Button btPrevious = new Button("Previous");
    protected final  int screenWidth = 1920;
    protected final  int screenHeight = 1000;

    Schedule schedule = new Schedule("221");
    Basket basket = new Basket();
    protected Stage stage;


    @Override // Override the start method in the Application class
    public void start(Stage stage) throws FileNotFoundException {
        this.stage = stage;
        buttonsSetup();
        basket.addSection(DataHandler.getAllowedSections().get(0));
        basket.addSection(DataHandler.getAllowedSections().get(1));
        schedule.addSectionToDays(DataHandler.getAllowedSections().get(0));
        schedule.addSectionToDays(DataHandler.getCourseOffering().get(34));
        SecondScene secondScene = new SecondScene(stage,schedule, basket, btPrevious, btSaveSchedule);
        stage.setScene(secondScene.getSchedule());

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void buttonsSetup() {
    }

    public class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == btNext) {
            }

            else if (actionEvent.getSource() == btPrevious) {
            }
            else if (actionEvent.getSource() == btLoadSchedule) {

            }
            else if (actionEvent.getSource() == btSaveSchedule) {



            }
        }
    }

}

