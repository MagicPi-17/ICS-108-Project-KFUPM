package com.example.termproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.termproject.classes.CSVReader;

public class app extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        CSVReader finishedCourses = new CSVReader("src/main/java/com/example/termproject/data/FinishedCourses.csv");
        try {
            String[][] degreePlanDataArray = finishedCourses.readTo2DArray();
            for (String[] row : degreePlanDataArray) {
                for (String ele : row) { System.out.print(ele + " ");}
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("File not found");;
        }
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1500, 790);
        stage.setTitle("Term_Project");
        stage.setScene(scene);
        stage.show();
    }
}
