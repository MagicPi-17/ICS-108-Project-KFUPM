package com.example.termproject;

import com.example.termproject.Scenes.FirstScene;
import com.example.termproject.Scenes.SecondScene;
import com.example.termproject.classes.Basket;
import com.example.termproject.classes.Schedule;
import com.example.termproject.dataHandlerClasses.DataHandler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
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
    private FirstScene firstScene = new FirstScene(btNext, btLoadSchedule);

    private SecondScene secondScene = new SecondScene(stage,schedule, basket, btPrevious, btSaveSchedule);



    @Override // Override the start method in the Application class
    public void start(Stage stage) throws FileNotFoundException {
        this.stage = stage;
        buttonsSetup(); //Give handler to each button
        setAddingToBasketScene(); //First scene

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setAddingToBasketScene() {
        try {
            HBox courseOfferingPane = firstScene.getCourseOfferingPane("Add Sections to Basket");

            Scene scene = new Scene(courseOfferingPane, screenWidth, screenHeight);
            stage.setScene(scene);
        }catch (Exception e) {
            System.out.println("Error!!\n" + e.getMessage());
        }
    }

    public void buttonsSetup() {
        ButtonHandler handler = new ButtonHandler();//adding handlers to the buttons
        btNext.setOnAction(handler);
        btPrevious.setOnAction(handler);
        btLoadSchedule.setOnAction(handler);
        btSaveSchedule.setOnAction(handler);
    }

    public class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == btNext) {
                stage.setScene(secondScene.getSchedule());
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

