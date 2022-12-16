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
import java.util.HashMap;

public class SecondScene {
    private final Button btPrevious;
    private final Button btSaveSchedule;
    private Schedule schedule;
    private final Basket basket;
    private final Stage stage;
    private final HashMap<String, Button> sectionsButtons;
    private HashMap<String, Integer> sectionsIndexes;
    private ArrayList<Button> addButtons = new ArrayList<>();
    private ArrayList<Button> removeButtons = new ArrayList<>();
    private ArrayList<String> clickedButtonsIDs;
    private VBox currentBasket;
    protected final  int screenWidth = 1920;
    protected final  int screenHeight = 1000;


    public SecondScene(Stage stage,Schedule schedule, Basket basket,Button btPrevious,
                       Button btSaveSchedule, HashMap<String, Integer> sectionsIndexes,
                       ArrayList<Button> addButtons, ArrayList<Button> removeButtons, ArrayList<String> clickedButtonsIDs) {
        this.stage = stage;
        this.schedule = schedule;
        this.basket = basket;
        this.btPrevious = btPrevious;
        this.btSaveSchedule = btSaveSchedule;
        this.sectionsIndexes = sectionsIndexes;
        this.addButtons = addButtons;
        this.removeButtons = removeButtons;
        this.clickedButtonsIDs = clickedButtonsIDs;
        sectionsButtons = new HashMap<>();

    }

    public void setSchedule(Schedule schedule) {this.schedule = schedule;}

    // create the second scene
    public Scene getSecondScene() {
        HBox hBox = new HBox(179);
        HBox schedule = getSchedule();
        currentBasket = getBasket();

        hBox.getChildren().add(schedule);
        hBox.getChildren().add(currentBasket);

        return new Scene(hBox, screenWidth, screenHeight);
    }

    // update the second scene without changing basket
    public Scene updateSecondScene() {
        HBox hBox = new HBox(179);
        HBox schedule = getSchedule();

        hBox.getChildren().add(schedule);
        hBox.getChildren().add(currentBasket);

        return new Scene(hBox, screenWidth, screenHeight);
    }

    // creating basket Vbox that contains basket's sections
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

            sectionsButtons.put(section.getCourse_section(), sectionBtn);
        }

        for(Section section : schedule.getSections()) {
            sectionsButtons.get(section.getCourse_section()).setDisable(true);
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
    // create stackPane with certain color and text
    public StackPane createShapeWithText(Double height, Double width, Color color, String text, Double fontSize) {
        StackPane stackPane = new StackPane();

        Rectangle rectangle = new Rectangle(width, height, color); rectangle.setStroke(Color.BLACK);
        Label word = new Label(text);
        word.setFont(Font.font(fontSize));
        stackPane.getChildren().addAll(rectangle, word);

        return stackPane;
    }

    // create a stackPane that contain label and rectangle and section info with certain dimensions
    public StackPane createCourseText(Section section,Double height, Double width) {
        StackPane stackPane = new StackPane();
        BorderPane borderPane = new BorderPane();
        Double[] colorValues = section.getColor();
        Color color = new Color(colorValues[0], colorValues[1], colorValues[2], colorValues[3]);
        Rectangle rectangle = new Rectangle(width, height, color); rectangle.setStroke(Color.BLACK);

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
    // create HBox that contain the schedule
    public HBox getSchedule() {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        String[] times = {"","7 am", "8 pm", "9 am", "10 am", "11 am", "12 pm",
                "1 pm", "2 pm", "3 pm", "4 pm", "5 pm"};

        // adjusting dimensions
        double width = 200;
        double height = 975/12;
        double scaleCorrection = (height+0.5)/60;

        ArrayList<Section>[] sectionsByDay = schedule.getScheduleByDays();
        int timeDifference;

        HBox hBox = new HBox();

        // setting the time VBox
        VBox timeBoxes = new VBox();
        for(String time : times) {
            timeBoxes.getChildren().add(createShapeWithText(height, width, Color.LIGHTGRAY,time, 20.0));
        }

        for(int i = 0; i < days.length; i++) {
            // the day start at 7 am
            Section previousSection = new Section("day Start", "0700-0700");
            FlowPane flowPane = new FlowPane();
            flowPane.setOrientation(Orientation.VERTICAL);
            flowPane.setVgap(0);
            flowPane.getChildren().add(createShapeWithText(height, width, Color.LIGHTGRAY,days[i], 20.0));
            for(Section section : sectionsByDay[i]) {
                timeDifference = previousSection.getTimeDifference(section);
                // add break between sections
                if (timeDifference > 0) {
                    String hours = (timeDifference/60 > 0) ? timeDifference/60+"h " : "";
                    String minutes = (timeDifference%60 > 0) ? timeDifference%60+"m" : "";
                    flowPane.getChildren().add(createShapeWithText((double)timeDifference * scaleCorrection, width, Color.LIGHTCYAN, hours + minutes, 16.0));
                }
                // add section with correct height
                flowPane.getChildren().add(createCourseText(section,section.getTimeDuration() * scaleCorrection, width));
                previousSection = section;

            }

            // flowPane.setMaxHeight(800);
            hBox.getChildren().add(flowPane);

        }

        HBox borderPaneMain = new HBox(0);
        borderPaneMain.getChildren().add(timeBoxes);
        borderPaneMain.getChildren().add(hBox);


        return borderPaneMain;


    }
    // handle to handle deleting sections from the schedule
    class deleteHandlerClass implements EventHandler<ActionEvent> {
        Section section;

        deleteHandlerClass(Section section){
            this.section = section;
        }
        public void handle(ActionEvent e) {
            schedule.removeSection(section);
            sectionsButtons.get(section.getCourse_section()).setDisable(false);
            stage.setScene(updateSecondScene());

        }
    }

    // Event handler that handle adding sections to schedule in the correct way
    class addHandlerClass implements EventHandler<ActionEvent> {
        Section section;

        addHandlerClass(Section section){
            this.section = section;
        }
        public void handle(ActionEvent e) {
            for(Section registeredSection : schedule.getSections()) {
                if(registeredSection.getCourse_section().split("-")[0].equals(section.getCourse_section().split("-")[0])
                        && registeredSection.getActivity().equals(section.getActivity())) {

                    schedule.removeSection(registeredSection);
                    boolean isAdd = schedule.addSectionToDays(section);
                    if(!isAdd) {
                        schedule.addSectionToDays(registeredSection);
                    }
                    else {
                    sectionsButtons.get(section.getCourse_section()).setDisable(true);
                    sectionsButtons.get(registeredSection.getCourse_section()).setDisable(false);
                    }
                    stage.setScene(updateSecondScene());
                    return;

                }
            }
            boolean isAdd = schedule.addSectionToDays(section);
            if (isAdd) { sectionsButtons.get(section.getCourse_section()).setDisable(true); }
            stage.setScene(updateSecondScene());

        }
    }


}
