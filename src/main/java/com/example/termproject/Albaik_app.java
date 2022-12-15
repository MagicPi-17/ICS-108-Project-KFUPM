package com.example.termproject;

import com.example.termproject.Scenes.FirstScene;
import com.example.termproject.Scenes.SecondScene;
import com.example.termproject.classes.Basket;
import com.example.termproject.classes.Schedule;
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

    private SecondScene secondScene;



    @Override // Override the start method in the Application class
    public void start(Stage stage) throws FileNotFoundException {
        this.stage = stage;
        secondScene = new SecondScene(stage,schedule, basket, btPrevious, btSaveSchedule);
        basket.addSection(DataHandler.getAllowedSections().get(0));
        basket.addSection(DataHandler.getAllowedSections().get(1));
        basket.addSection(DataHandler.getAllowedSections().get(2));
        basket.addSection(DataHandler.getAllowedSections().get(3));
        basket.addSection(DataHandler.getAllowedSections().get(4));
        basket.addSection(DataHandler.getAllowedSections().get(5));
        basket.addSection(DataHandler.getAllowedSections().get(6));
        basket.addSection(DataHandler.getAllowedSections().get(7));
        basket.addSection(DataHandler.getAllowedSections().get(8));
        basket.addSection(DataHandler.getAllowedSections().get(9));
        basket.addSection(DataHandler.getAllowedSections().get(10));
        basket.addSection(DataHandler.getAllowedSections().get(11));
        basket.addSection(DataHandler.getAllowedSections().get(12));

        schedule.addSectionToDays(DataHandler.getAllowedSections().get(0));




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
                setAddingToBasketScene();
            }
            else if (actionEvent.getSource() == btLoadSchedule) {

            }
            else if (actionEvent.getSource() == btSaveSchedule) {



            }
        }
    }

}

