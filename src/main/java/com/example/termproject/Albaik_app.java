package com.example.termproject;

import com.example.termproject.classes.FinishedCourse;
import com.example.termproject.classes.Student;
import com.example.termproject.readWriteDataClasses.DataReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Albaik_app extends Application {
    protected final Button btSchedule = new Button("Schedule");
    protected final Button btBasket = new Button("Basket");
    protected final  Button btHome = new Button("Home");
    protected final Button btStart = new Button("Start");
    protected final  int screenWidth = 1920;
    protected final  int screenHeight = 1000;
    protected   Stage stage;
    FinishedCourse finishedCourse;


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
        HBox columnData = new HBox();
        try {
            Student studentData = DataReader.getStudentFinishedCourse();
            ArrayList<Integer> termsSorted = studentData.getTermsSorted();

            VBox[] vBoxes = new VBox[termsSorted.size()];
            for (int row =0; row < termsSorted.size(); row++) {
                Label text = new Label("Term " + termsSorted.get(row));
                vBoxes[row] = new VBox(10);
                vBoxes[row].setSpacing(10);
                vBoxes[row].setPadding(new Insets(15, 15, 15, 15));
                vBoxes[row].setAlignment(Pos.TOP_LEFT);
                vBoxes[row].getChildren().add(text);}

            for (int i = 0; i < studentData.getFinishedCourses().size(); i++) {
                int term = studentData.getTermOfCourse(i);
                Label text = new Label(studentData.getStringFinishedCourse(i));

                int termLocationIndex = termsSorted.indexOf(term);

                StackPane stack = new StackPane();
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(60);
                rectangle.setWidth(100);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.WHITE);
                stack.getChildren().addAll(rectangle, text);

                vBoxes[termLocationIndex].getChildren().add(stack);
            }

            for (VBox vBox : vBoxes) { columnData.getChildren().add(vBox);}

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(columnData);
        borderPane.setLeft(createButtonsBar());
        Scene scene = new Scene(borderPane, screenWidth, screenHeight);
        stage.setScene(scene);

    }
    // example page design

    public StackPane createShapeWithText(Double height, Double width,Color color, String text) {
        StackPane stackPane = new StackPane();

        Rectangle rectangle = new Rectangle(width, height, color); rectangle.setStroke(Color.BLACK);
        Label word = new Label(text);
        word.setFont(Font.font(20));
        stackPane.getChildren().addAll(rectangle, word);

        return stackPane;
    }

    public StackPane createCourseText(Double height, Double width,Color color, String text) {
        StackPane stackPane = new StackPane();
        BorderPane borderPane = new BorderPane();
        Rectangle rectangle = new Rectangle(width, height, color); rectangle.setStroke(Color.BLACK);

        Label word = new Label(text);


        Button btn = new Button("del");
        btn.resize(5,5);
        btn.setAlignment(Pos.BASELINE_RIGHT);

        word.setFont(Font.font(20));

        borderPane.setCenter(word);
        borderPane.setBottom(btn);
        stackPane.getChildren().addAll(rectangle, borderPane);

        return stackPane;
    }
    public void setSchedule() {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        String[] times = {"","7 am", "8 pm", "9 am", "10 am", "11 am", "12 pm",
                        "1 pm", "2 pm", "3 pm", "4 pm", "5 pm"};

        HBox hBox = new HBox();

        VBox timeBoxes = new VBox();
        for(String time : times) {
            timeBoxes.getChildren().add(createShapeWithText(70.0, 150.0, Color.LIGHTGRAY,time));
        }

        for(int i = 0; i < 5; i++) {
           FlowPane flowPane = new FlowPane();
           flowPane.setOrientation(Orientation.VERTICAL);
           flowPane.setVgap(0);
           StackPane temp = new StackPane();
           temp = createCourseText(210.0 - 35, 150.0, Color.LIGHTGREEN,"class time");

           flowPane.getChildren().add(createShapeWithText(70.0, 150.0, Color.LIGHTCORAL,days[i]));

           flowPane.getChildren().add(createShapeWithText(70.0, 150.0, Color.LIGHTGRAY,"empty"));
            if (i == 1) flowPane.getChildren().add(temp);
            else flowPane.getChildren().add(createShapeWithText(210.0 - 35, 150.0, Color.LIGHTGREEN,"class time"));
           hBox.getChildren().add(flowPane);

        }

        BorderPane borderPaneMain = new BorderPane();
        borderPaneMain.setLeft(timeBoxes);
        borderPaneMain.setCenter(hBox);

        borderPaneMain.setRight(createButtonsBar());
        Scene scene = new Scene(borderPaneMain, screenWidth, screenHeight);
        stage.setScene(scene);

    }

    // example page design
    public void setScene2() {
        HBox hBox = new HBox(10);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 15, 15, 15));
        hBox.setAlignment(Pos.TOP_LEFT);

        btBasket.setOnAction(new ButtonHandler());
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hBox);
        borderPane.setRight(createButtonsBar());
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
                setSchedule();
            }
            else if (actionEvent.getSource() == btBasket) {
                setFinishedCoursesPage();


            }
        }
    }
}
