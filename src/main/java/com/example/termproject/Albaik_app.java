package com.example.termproject;

import com.example.termproject.classes.Student;
import com.example.termproject.readWriteDataClasses.DataReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Albaik_app extends Application {
    protected final Button btSchedule = new Button("Schedule");
    protected final Button btBasket = new Button("Basket");
    protected final  Button btHome = new Button("Home");
    protected final Button btStart = new Button("Start");
    protected final  int screenWidth = 1200;
    protected final  int screenHeight = 900;
    protected   Stage stage;


    @Override // Override the start method in the Application class
    public void start(Stage stage) {
        this.stage = stage;
        setupBarButtons();
        setHomePage();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void setupBarButtons(){
        btHome.setOnAction(new ButtonHandler());
        btSchedule.setOnAction(new ButtonHandler());
        btBasket.setOnAction(new ButtonHandler());
        int size = 50;
        int btPreviousSize = size - btBasket.getText().length();
        int btNextSize = size - btSchedule.getText().length();
        int btHomeSize = size - btHome.getText().length();
        btHome.setPadding(new Insets(btHomeSize,btHomeSize,btHomeSize,btHomeSize));
        btSchedule.setPadding(new Insets(btNextSize, btNextSize,btNextSize,btNextSize));
        btBasket.setPadding(new Insets(btPreviousSize,btPreviousSize- 5,btPreviousSize,btPreviousSize - 5));


    }


    // create VBox that contain buttons that switches to another pages
    public VBox createButtonsBar() {
        VBox vBox = new VBox(10);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(15, 0, 0, 0));
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setSpacing(50);
        vBox.getChildren().add(btHome);
        vBox.getChildren().add(btSchedule);
        vBox.getChildren().add(btBasket);
        return vBox;
    }

    // home page design
    public void setHomePage() {
        VBox vBoxBar = createButtonsBar();
        VBox vboxText = new VBox(10);

        VBox vBoxRight = new VBox(10);
        vBoxRight.setSpacing(10);
        vBoxRight.setPadding(new Insets(65, 65, 65, 65));

        vboxText.setAlignment(Pos.TOP_CENTER);
        vboxText.setSpacing(10);
        vboxText.setPadding(new Insets(75,15,15,15));

        Label text1 = new Label("ICS 108 Project");
        Label text2 = new Label("Student scheduler");
        text1.setFont(Font.font(75));
        text2.setFont(Font.font(50));

        vboxText.getChildren().add(text1);
        vboxText.getChildren().add(text2);

        btStart.setAlignment(Pos.CENTER_LEFT);
        btStart.setPadding(new Insets(50, 50, 50, 50));
        BorderPane borderPane = new BorderPane();



        borderPane.setLeft(vBoxBar);
        borderPane.setCenter(btStart);
        borderPane.setRight(vBoxRight);
        borderPane.setTop(vboxText);


        Scene scene = new Scene(borderPane, screenWidth, screenHeight);
        stage.setScene(scene);
    }


    public void setFinishedCoursesPage() {
        VBox vBoxBar = createButtonsBar();
        VBox vBoxCourses = new VBox(10);
        vBoxCourses.setSpacing(10);
        vBoxCourses.setPadding(new Insets(15, 15, 15, 15));
        vBoxCourses.setAlignment(Pos.TOP_LEFT);

        try {
            Student studentData = DataReader.getFinishedCourse();
            for (int i = 0; i < studentData.getFinishedCourses().size(); i++) {
                vBoxCourses.getChildren().add(new Label(studentData.getStringFinishedCourse(i)));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }




        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBoxCourses);
        borderPane.setLeft(createButtonsBar());
        Scene scene = new Scene(borderPane, screenWidth, screenHeight);
        stage.setScene(scene);

    }
    // example page design
    public void setScene1() {
        HBox hBox = new HBox(10);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(btSchedule);

        btSchedule.setOnAction(new ButtonHandler());
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hBox);
        borderPane.setLeft(createButtonsBar());
        Scene scene = new Scene(borderPane, screenWidth, screenHeight);
        stage.setScene(scene);

    }

    // example page design
    public void setScene2() {
        HBox hBox = new HBox(10);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(btBasket);

        btBasket.setOnAction(new ButtonHandler());
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hBox);
        borderPane.setLeft(createButtonsBar());
        Scene scene = new Scene(borderPane, screenWidth, screenHeight);
        stage.setScene(scene);
    }


    // using bar button to switch between pages
    public class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            if (actionEvent.getSource() == btHome) {
                setHomePage();
            }

            else if (actionEvent.getSource() == btStart) {
                setHomePage();
            }
            else if (actionEvent.getSource() == btSchedule) {
                setFinishedCoursesPage();

            }
            else if (actionEvent.getSource() == btBasket) {
                setScene1();

            }
        }
    }
}
