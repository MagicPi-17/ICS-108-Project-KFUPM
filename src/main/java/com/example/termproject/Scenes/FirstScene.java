package com.example.termproject.Scenes;

import com.example.termproject.classes.Basket;
import com.example.termproject.classes.Section;
import com.example.termproject.dataHandlerClasses.DataHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class FirstScene {
    private ArrayList<Button> addButtons = new ArrayList<>();
    private ArrayList<Button> removeButtons = new ArrayList<>();
    private Basket myBasket = new Basket();
    private FiltratedSections filtrated = new FiltratedSections();
    public final int stageWidth = 1920;
    private Button nextButton;
    private Button loadScheduleButton;

    public FirstScene(Button next, Button load) {
        this.nextButton = next;
        this.loadScheduleButton = load;
    }

    public HBox getDataInGridPane() throws IOException{ //This method get the data and put them in a gridPane
        HBox finalPane = new HBox();
        finalPane.setPrefWidth(stageWidth);
        finalPane.setAlignment(Pos.BASELINE_CENTER);
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
            filtiredIn2D[i + 1][5] = filtired.get(i).getDays();
            filtiredIn2D[i + 1][6] = filtired.get(i).getTime();
            filtiredIn2D[i + 1][7] = filtired.get(i).getLocation();
            filtiredIn2D[i + 1][8] = filtired.get(i).getStatus();
            filtiredIn2D[i + 1][9] = filtired.get(i).getWaitlist();
        }

        GridPane grid = new GridPane();
        for (int i = 0; i < filtiredIn2D.length; i++) {
            for (int j = 0; j < filtiredIn2D[i].length; j++) {
                Label info = new Label(filtiredIn2D[i][j] + "         ");
                info.setFont(Font.font ("Verdana", 16));
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
        finalPane.getChildren().add(grid);
        return finalPane;
    }



    public HBox getTitleButtonsPane(String title) {
        HBox finalPane = new HBox();
        HBox centringPane = new HBox();
        centringPane.setAlignment(Pos.BASELINE_CENTER);
        centringPane.setPrefWidth(stageWidth);

        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(stageWidth * 3 / 5);
        borderPane.setPadding(new Insets(50,0,15,0));
        //Title
        Label degreePlanTitle = new Label(title);
        degreePlanTitle.setFont(Font.font ("Verdana", 22));
        borderPane.setCenter(degreePlanTitle);
        //Buttons
        nextButton.setPrefSize(220, 100);
        nextButton.setFont((Font.font ("Verdana", 20)));

        loadScheduleButton.setPrefSize(220, 100);
        loadScheduleButton.setFont((Font.font ("Verdana", 18)));
        loadScheduleButton.setPadding(new Insets(0,5,0,5));

        borderPane.setRight(nextButton);
        borderPane.setLeft(loadScheduleButton);
        centringPane.getChildren().add(borderPane);
        finalPane.getChildren().add(centringPane);

        return  finalPane;
    }



    public HBox getCourseOfferingPane(String title) throws IOException{
        HBox finalPane = new HBox();
        finalPane.setAlignment(Pos.BASELINE_CENTER);

        VBox vpane = new VBox(); //The pane is placing its children panes vertically
        vpane.setPrefWidth(stageWidth);
        vpane.setPadding(new Insets(0,40,0,0));
        vpane.setSpacing(50);

        HBox titleAndButtonsPane = getTitleButtonsPane(title);

        HBox sectionsPane = getDataInGridPane();

        vpane.getChildren().add(titleAndButtonsPane); //adding the title to the pane
        vpane.getChildren().add(sectionsPane); //adding the list of plan courses to the pane

        ScrollPane scrollPane = new ScrollPane(vpane); //Right section(COURSE OFFERING)
        finalPane.getChildren().add(scrollPane);
        //Fit the scrollpane in parent pane
        scrollPane.prefWidthProperty().bind(finalPane.widthProperty());
        scrollPane.prefHeightProperty().bind(finalPane.heightProperty());

        return finalPane;
    }


    public ArrayList<Section> getBasketSections() { //To give the Basket to the second scene
        return this.myBasket.getSections();
    }


    class FiltratedSections { //To get the allowed sections to register
        private ArrayList<Section> filtrated;
        FiltratedSections() {
            try {
                ArrayList<Section> filtired = DataHandler.getAllowedSections();
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
