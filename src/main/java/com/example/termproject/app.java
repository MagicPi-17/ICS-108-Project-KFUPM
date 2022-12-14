package com.example.termproject;

import com.example.termproject.classes.FirstScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class app extends Application {
    private Stage stage;
    private FirstScene firstScene = new FirstScene();
    private final int stageWidth = firstScene.stageWidth;



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Term_Project");

        setAddingToBasketScene();
        this.stage.show();
    }

    public void setAddingToBasketScene() {
        try {
            HBox courseOfferingPane = firstScene.getCourseOfferingPane("Add Sections to Basket");

            Scene scene = new Scene(courseOfferingPane, stageWidth, 1000);
            stage.setScene(scene);
        }catch (Exception e) {
            System.out.println("Error!!\n" + e.getMessage());
        }
    }

}