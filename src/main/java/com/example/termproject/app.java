package com.example.termproject;

import com.example.termproject.classes.Basket;
import com.example.termproject.classes.Schedule;
import com.example.termproject.readWriteDataClasses.DataReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.example.termproject.classes.CSVReader;
import com.example.termproject.classes.Section;

public class app extends Application {
    private ArrayList<Button> addButtons = new ArrayList<>();
    private ArrayList<Button> removeButtons = new ArrayList<>();
    private Basket myBasket = new Basket();
    private FiltratedSections filtrated = new FiltratedSections();
    private Stage stage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Term_Project");

        setAddToBasketScene();
        this.stage.show();
    }

    public void setAddToBasketScene() {
        try {
            HBox courseOfferingPane = getCourseOfferingPane("Add Sections to Basket");

            Scene scene = new Scene(courseOfferingPane, 1920, 1000);
            stage.setScene(scene);
        }catch (IOException e) {
            System.out.println("Error!!\n" + e.getMessage());
        }
    }
    public GridPane getDataInGridPane() throws IOException{ //This method get the data and put them in a gridPane
        // Converting the List to 2DArray
        ArrayList<Section> filtired = filtrated.getFiltratedSections();
        String[][] filtiredIn2D = new String[filtired.size() + 1][10];
        filtiredIn2D[0][0] = "Course-Sec";
        filtiredIn2D[0][1] = "Activity";
        filtiredIn2D[0][2] = "CRN";
        filtiredIn2D[0][3] = "Course Name";
        filtiredIn2D[0][4] = "Instructor";
        filtiredIn2D[0][5] = "Day";
        filtiredIn2D[0][6] = "Time";
        filtiredIn2D[0][7] = "Location";
        filtiredIn2D[0][8] = "Status";
        filtiredIn2D[0][9] = "Waitlist";
        for (int i = 0; i <filtired.size(); i++) {
            filtiredIn2D[i + 1][0] = filtired.get(i).getCourse_section();
            filtiredIn2D[i + 1][1] = filtired.get(i).getActivity();
            filtiredIn2D[i + 1][2] = filtired.get(i).getCRN();
            filtiredIn2D[i + 1][3] = filtired.get(i).getCourseName();
            filtiredIn2D[i + 1][4] = filtired.get(i).getInstructor();
            filtiredIn2D[i + 1][5] = filtired.get(i).getDay();
            filtiredIn2D[i + 1][6] = filtired.get(i).getTime();
            filtiredIn2D[i + 1][7] = filtired.get(i).getLocation();
            filtiredIn2D[i + 1][8] = filtired.get(i).getStatus();
            filtiredIn2D[i + 1][9] = filtired.get(i).getWaitlist();
        }

        GridPane grid = new GridPane();
        for (int i = 0; i < filtiredIn2D.length; i++) {
            for (int j = 0; j < filtiredIn2D[i].length; j++) {
                Label info = new Label( "       " + filtiredIn2D[i][j] + "    ");
                info.setFont(Font.font ("Verdana", 14));
                info.setPadding(new Insets(15, 0, 0, 0));
                HBox infoPane = new HBox(); //To the data in their boxes
                infoPane.getChildren().add(info);
                infoPane.setAlignment(Pos.BASELINE_CENTER);
                grid.add(infoPane, j, i);
            }
            //Adding buttons at the end
            if (i > 0) {
                Button addBtn = new Button("Add"); 
                addBtn.setPadding(new Insets(15, 15, 15, 15));
                addBtn.setId(Integer.toString(i)); //Give each button its id
                AddHandlerClass handler1 = new AddHandlerClass();
                addBtn.setOnAction(handler1);
                addBtn.setPrefWidth(70);
                addBtn.setFont((Font.font ("Verdana", 14)));
                grid.add(addBtn, filtiredIn2D[i].length, i);
                addButtons.add(addBtn); //Adding the button to the public list of buttons

                Button removeBtn = new Button("Remove");
                removeBtn.setPadding(new Insets(15, 15, 15, 15));
                removeBtn.setId(Integer.toString(i)); //Give each button its id
                RemoveHandlerClass handler2 = new RemoveHandlerClass();
                removeBtn.setOnAction(handler2);
                removeBtn.setDisable(true);
                removeBtn.setPrefWidth(100);
                removeBtn.setFont((Font.font ("Verdana", 15)));
                grid.add(removeBtn, filtiredIn2D[i].length + 2, i);
                removeButtons.add(removeBtn); //Adding the button to the public list of buttons
            }
        }
        return grid;
    }


    public HBox getCourseOfferingPane(String title) throws IOException{
        HBox finalPane = new HBox();
        HBox courseOfferingPane = new HBox();
        courseOfferingPane.setPrefWidth(1720);
        VBox pane = new VBox(); //The pane is placing its children panes vertically
        pane.setPadding(new Insets(0,40,0,0));
        pane.setSpacing(50);

        HBox degreePlanTitlePane = new HBox(); //Title
        degreePlanTitlePane.setAlignment(Pos.BASELINE_CENTER);
        degreePlanTitlePane.setPadding(new Insets(50,0,15,0));
        Label degreePlanTitle = new Label(title);
        degreePlanTitle.setFont(Font.font ("Verdana", 20));
        degreePlanTitlePane.getChildren().add(degreePlanTitle);

        GridPane planCoursesPane = getDataInGridPane();

        pane.getChildren().add(degreePlanTitlePane); //adding the title to the pane 
        pane.getChildren().add(planCoursesPane); //adding the list of plan courses to the pane

        //Buttons Section
        VBox buttonsSection = new VBox();
        buttonsSection.setPrefWidth(200);
        Button nextButton = new Button("Next");
        Button loadSavedButton = new Button("Load Saved Schedule");
//        Button myPlanButton = new Button("My Plan Courses");

        buttonsSection.getChildren().add(nextButton);
        buttonsSection.getChildren().add(loadSavedButton);
//        buttonsSection.getChildren().add(myPlanButton);

        finalPane.getChildren().add(buttonsSection);
        nextButton.setPrefSize(200, 150);
        loadSavedButton.setPrefSize(200, 150);
//        myPlanButton.setPrefSize(200,150);
        nextButton.setFont((Font.font ("Verdana", 20)));
        loadSavedButton.setFont((Font.font ("Verdana", 16)));
//        myPlanButton.setFont((Font.font ("Verdana", 17)));

        ScrollPane scrollPane = new ScrollPane(pane); //Right section(COURSE OFFERING)
        courseOfferingPane.getChildren().add(scrollPane);
        //Fit the scrollpane in parent pane
        scrollPane.prefWidthProperty().bind(courseOfferingPane.widthProperty());
        scrollPane.prefHeightProperty().bind(courseOfferingPane.heightProperty());

        finalPane.getChildren().add(courseOfferingPane);

        return finalPane;
    }

    class FiltratedSections {
        private ArrayList<Section> filtrated;
        FiltratedSections() {
            try {
                ArrayList<Section> filtired = DataReader.getAllowedSections();
                this.filtrated = filtired;
            } catch (FileNotFoundException e) {
                System.out.println("ERROR IN GETTING FILTIRED SECTIONS");
            }
        }

        public ArrayList<Section> getFiltratedSections() {
            return this.filtrated;
    }
}

    class AddHandlerClass implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Button numberButton = (Button) e.getTarget();
            numberButton.setDisable(true);
            (removeButtons.get(Integer.parseInt(numberButton.getId()) - 1)).setDisable(false); //Enabling the remove

            System.out.println("ADD " + numberButton.getId()); //Add the section to the basket
            myBasket.addSection(filtrated.getFiltratedSections().get(Integer.parseInt(numberButton.getId()) - 1));
            System.out.println(myBasket.getSections());
        }
    }

    class RemoveHandlerClass implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Button numberButton = (Button) e.getTarget();
            numberButton.setDisable(true);
            (addButtons.get(Integer.parseInt(numberButton.getId()) - 1)).setDisable(false); //Enabling the add

            System.out.println("Remove " + numberButton.getId()); //Remove the section from the basket
            myBasket.removeSection(filtrated.getFiltratedSections().get(Integer.parseInt(numberButton.getId()) - 1));
        }
    }
}