package com.example.termproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import com.example.termproject.classes.CSVReader;
import com.example.termproject.classes.Section;

public class app extends Application {
    public ArrayList<Button> addButtons = new ArrayList<>();
    public ArrayList<Button> removeButtons = new ArrayList<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        HBox mainPane = new HBox(); // Used this pane to center the content horizantilly
        mainPane.setAlignment(Pos.BASELINE_CENTER);

        Scene scene = new Scene(mainPane, 1366, 768);
        stage.setTitle("Term_Project");
        stage.setScene(scene);
        stage.show();

        try {
            VBox degreePlanCoursesPane = getDataTitleInPane("src/main/java/com/example/termproject/data/CourseOffering.csv", "Course Offering");
            mainPane.getChildren().add(degreePlanCoursesPane); //adding the plan courses list to the main pane
        }catch (IOException e) {
            System.out.println("File not Found");
        }     
    }

    public GridPane getDataInGridPane(String filePath) throws IOException{ //This method get the data and put them in a gridPane
        CSVReader originalData = new CSVReader(filePath);
        String[][] dataArray = originalData.readTo2DArray();
        GridPane grid = new GridPane();
        for (int i = 0; i < dataArray.length; i++) {
            for (int j = 0; j < dataArray[i].length; j++) {
                Label info = new Label(dataArray[i][j] + "          ");
                info.setFont(Font.font ("Verdana", 12));
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
                grid.add(addBtn, dataArray[i].length, i);
                addButtons.add(addBtn); //Adding the button to the public list of buttons

                Button removeBtn = new Button("Remove"); 
                removeBtn.setPadding(new Insets(15, 15, 15, 15));
                removeBtn.setId(Integer.toString(i)); //Give each button its id
                RemoveHandlerClass handler2 = new RemoveHandlerClass();
                removeBtn.setOnAction(handler2);
                removeBtn.setDisable(true);
                grid.add(removeBtn, dataArray[i].length + 2, i);
                removeButtons.add(removeBtn); //Adding the button to the public list of buttons
            }
        }
        return grid;
    }


    public VBox getDataTitleInPane(String path, String title) throws IOException{
        VBox pane = new VBox(); //The pane is placing its children panes vertically
        pane.setSpacing(50);

        HBox degreePlanTitlePane = new HBox(); //Title
        degreePlanTitlePane.setAlignment(Pos.BASELINE_CENTER);
        degreePlanTitlePane.setPadding(new Insets(50,0,15,0));
        Label degreePlanTitle = new Label(title);
        degreePlanTitle.setFont(Font.font ("Verdana", 20));
        degreePlanTitlePane.getChildren().add(degreePlanTitle);

        GridPane planCoursesPane = getDataInGridPane(path);

        pane.getChildren().add(degreePlanTitlePane); //adding the title to the pane 
        pane.getChildren().add(planCoursesPane); //adding the list of plan courses to the pane

        return pane;
    }

    class Basket {
        private ArrayList<Section> sections = new ArrayList<>();

        public void addSection(Section section) {
            sections.add(section);
        }
        
        public ArrayList<Section> getSections() {
            return this.sections;
        }
    }

    class AddHandlerClass implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Button numberButton = (Button) e.getTarget();
            numberButton.setDisable(true);
            (removeButtons.get(Integer.parseInt(numberButton.getId()) - 1)).setDisable(false); //Enabling the remove

            System.out.println("ADD " + numberButton.getId()); //Add the section to the basket
        }
    }

    class RemoveHandlerClass implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Button numberButton = (Button) e.getTarget();
            numberButton.setDisable(true);
            (addButtons.get(Integer.parseInt(numberButton.getId()) - 1)).setDisable(false); //Enabling the add

            System.out.println("Remove " + numberButton.getId()); //Remove the section from the basket
        }
    }
}