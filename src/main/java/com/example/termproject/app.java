package com.example.termproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.termproject.classes.CSVReader;

public class app extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        HBox mainPane = new HBox(); // Used this pane to center the content horizantilly
        mainPane.setAlignment(Pos.BASELINE_CENTER);

        Scene scene = new Scene(mainPane, 1400, 740);
        stage.setTitle("Term_Project");
        stage.setScene(scene);
        stage.show();

        try {
            VBox degreePlanCoursesPane = getDegreePlanCoursesPane();
            mainPane.getChildren().add(degreePlanCoursesPane); //adding the plan courses list to the main pane
        }catch (IOException e) {
            System.out.println("DegreePlan File not Found");
        }
    }


    public VBox getDegreePlanCoursesPane() throws IOException{
        CSVReader finishedCourses = new CSVReader("src/main/java/com/example/termproject/data/DegreePlan.csv");
        String[][] degreePlanDataArray = finishedCourses.readTo2DArray();
        GridPane planCoursesPane = new GridPane();
        for (int i = 0; i < degreePlanDataArray.length; i++) {
            for (int j = 0; j < degreePlanDataArray[0].length; j++) {
                Label courseInfo = new Label(degreePlanDataArray[i][j] + "             ");
                courseInfo.setFont(Font.font ("Verdana", 16));
                courseInfo.setPadding(new Insets(10, 0, 0, 0));
                planCoursesPane.add(courseInfo, j, i);
            }
        }
        
        VBox pane = new VBox(); //The pane is placing its children panes vertically
        pane.setSpacing(50);

        HBox degreePlanTitlePane = new HBox(); //Title
        degreePlanTitlePane.setAlignment(Pos.BASELINE_CENTER);
        degreePlanTitlePane.setPadding(new Insets(50,0,30,0));
        Label degreePlanTitle = new Label("Degree Plan Courses");
        degreePlanTitle.setFont(Font.font ("Verdana", 20));
        degreePlanTitlePane.getChildren().add(degreePlanTitle);

        pane.getChildren().add(degreePlanTitlePane); //adding the title to the pane 
        pane.getChildren().add(planCoursesPane); //adding the list of plan courses to the pane

        return pane;
    }
}
