package com.example.termproject.Scenes;

import com.example.termproject.classes.Basket;
import com.example.termproject.classes.Schedule;
import com.example.termproject.classes.Section;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SecondScene {
    private Button btPrevious;
    private Button btSaveSchedule;
    private Schedule schedule;
    private Basket basket;
    private Stage stage;
    protected final  int screenWidth = 1920;
    protected final  int screenHeight = 1000;


    public SecondScene(Stage stage,Schedule schedule, Basket basket,Button btPrevious, Button btSaveSchedule) {
        this.stage = stage;
        this.schedule = schedule;
        this.basket = basket;
        this.btPrevious = btPrevious;
        this.btSaveSchedule = btSaveSchedule;

    }


    public Scene getSecondScene() {
        HBox hBox = new HBox(1920 - 175 * 6 - 540);
        HBox schedule = getSchedule();
        VBox basket = getBasket();

        hBox.getChildren().add(schedule);
        hBox.getChildren().add(basket);

        Scene scene = new Scene(hBox, screenWidth, screenHeight);
        return scene;
    }


    public VBox getBasket(){
        VBox vBox = new VBox(10);
        HBox buttons = new HBox();
        buttons.setSpacing(100);
        buttons.getChildren().add(btPrevious);
        buttons.getChildren().add(btSaveSchedule);


        VBox sections = new VBox();
        for(Section section : basket.getSections()) {
            Button sectionBtn = new Button(section.getScheduleText());
            sectionBtn.setFont(Font.font ("Verdana", 15));
            sectionBtn.setPrefSize(540, 100);
            sectionBtn.setOnAction(new addHandlerClass(section));
            sections.getChildren().add(sectionBtn);
        }

        BorderPane borderPane = new BorderPane(sections);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(borderPane);
        scrollPane.prefWidthProperty().bind(borderPane.widthProperty());
        scrollPane.prefHeightProperty().bind(borderPane.heightProperty());
        vBox.getChildren().add(buttons);
        vBox.getChildren().add(scrollPane);

        return vBox;

    }

    public StackPane createShapeWithText(Double height, Double width, Color color, String text) {
        StackPane stackPane = new StackPane();

        Rectangle rectangle = new Rectangle(width, height, color); rectangle.setStroke(Color.BLACK);
        Label word = new Label(text);
        word.setFont(Font.font(16));
        stackPane.getChildren().addAll(rectangle, word);

        return stackPane;
    }


    public StackPane createCourseText(Section section,Double height, Double width) {
        StackPane stackPane = new StackPane();
        BorderPane borderPane = new BorderPane();
        Rectangle rectangle = new Rectangle(width, height, section.getColor()); rectangle.setStroke(Color.BLACK);

        Label word = new Label(section.getScheduleText());


        Button btn = new Button("del");
        btn.resize(5,5);
        btn.setAlignment(Pos.BOTTOM_RIGHT);
        btn.setOnAction(new deleteHandlerClass(section));

        word.setFont(Font.font(16));
        borderPane.setRight(btn);
        borderPane.setCenter(word);

        stackPane.getChildren().addAll(rectangle, borderPane);

        return stackPane;
    }
    public HBox getSchedule() {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        String[] times = {"","7 am", "8 pm", "9 am", "10 am", "11 am", "12 pm",
                "1 pm", "2 pm", "3 pm", "4 pm", "5 pm"};


        double width = 175;
        double height = 80;
        double scaleCorrection = (height+0.5)/60;

        System.out.println("start");
        for(Section section : schedule.getSections().keySet()) {
            System.out.println("1" + section.toString());

        }        ArrayList<Section>[] sectionsByDay = schedule.getScheduleByDays();
        int timeDifference;

        HBox hBox = new HBox();


        VBox timeBoxes = new VBox();
        for(String time : times) {
            timeBoxes.getChildren().add(createShapeWithText(height, width, Color.LIGHTGRAY,time));
        }

        for(int i = 0; i < days.length; i++) {
            Section previousSection = new Section("day Start", "0700-0700");
            FlowPane flowPane = new FlowPane();
            flowPane.setOrientation(Orientation.VERTICAL);
            flowPane.setVgap(0);

            flowPane.getChildren().add(createShapeWithText(height, width, Color.LIGHTCORAL,days[i]));
            System.out.println(sectionsByDay[i].size() + " size");
            for(Section section : sectionsByDay[i]) {
                System.out.println(section.getScheduleText() + " " + i);
                timeDifference = previousSection.getTimeDifference(section);
                if (timeDifference > 0) {
                    flowPane.getChildren().add(createShapeWithText((double)timeDifference * scaleCorrection, width, Color.LIGHTCYAN, "break" + timeDifference));
                }
                flowPane.getChildren().add(createCourseText(section,section.getTimeDuration() * scaleCorrection, width));
                previousSection = section;

            }

            flowPane.setMaxHeight(800);
            hBox.getChildren().add(flowPane);

        }

        HBox borderPanreMain = new HBox(0);
        borderPanreMain.getChildren().add(timeBoxes);
        borderPanreMain.getChildren().add(hBox);


        return borderPanreMain;


    }

    class deleteHandlerClass implements EventHandler<ActionEvent> {
        Section section;

        deleteHandlerClass(Section section){
            this.section = section;
        }
        public void handle(ActionEvent e) {
            schedule.removeSection(section);
            stage.setScene(getSecondScene());

        }
    }

    class addHandlerClass implements EventHandler<ActionEvent> {
        Section section;

        addHandlerClass(Section section){
            this.section = section;
        }
        public void handle(ActionEvent e) {
            schedule.addSectionToDays(section);
            stage.setScene(getSecondScene());

        }
    }


}