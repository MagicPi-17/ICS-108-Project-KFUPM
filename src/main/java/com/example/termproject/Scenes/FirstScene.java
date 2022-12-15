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
import java.util.HashMap;

public class FirstScene {
    private ArrayList<Button> addButtons;
    private ArrayList<Button> removeButtons;
    private Basket myBasket;
    private ArrayList<Section> filtrated;
    public final int stageWidth = 1920;
    private Button nextButton;
    private Button loadScheduleButton;
    private ArrayList<String> clickedButtonsIDs = new ArrayList<>();
    private HashMap<Section, Integer> sectionsIndexes = new HashMap<>();


    public FirstScene(Button next, Button load, Basket basket, ArrayList<Button> addButtons, ArrayList<Button> removeButtons, ArrayList<String> clickedButtonsIDs, HashMap<Section, Integer> sectionsIndexes) {
        this.nextButton = next;
        this.loadScheduleButton = load;
        this.myBasket = basket;
        this.addButtons = addButtons;
        this.removeButtons = removeButtons;
        this.filtrated = getFiltratedSections();
        this.clickedButtonsIDs = clickedButtonsIDs;
        this.sectionsIndexes = sectionsIndexes;
    }

    public HBox getDataInGridPane() throws IOException{ //This method get the data and put them in a gridPane
        HBox finalPane = new HBox();
        finalPane.setPrefWidth(stageWidth);
        finalPane.setAlignment(Pos.BASELINE_CENTER);
        // Converting the List to 2DArray
        String[][] filtiredIn2D = new String[filtrated.size() + 1][10];

        AddHandlerClass addingHandler = new AddHandlerClass(); //Creating handlers for adding and removing
        RemoveHandlerClass removingHandler = new RemoveHandlerClass();

        String[] titles = {"Course-Sec","Activity","CRN","Course Name","Instructor","Day","Time","Location","Status","Waitlist"};
        for (int i = 0; i<titles.length; i++) {
            filtiredIn2D[0][i] = titles[i];
        }

        for (int i = 0; i <filtrated.size(); i++) {
            filtiredIn2D[i + 1][0] = filtrated.get(i).getCourse_section();
            filtiredIn2D[i + 1][1] = filtrated.get(i).getActivity();
            filtiredIn2D[i + 1][2] = filtrated.get(i).getCRN();
            filtiredIn2D[i + 1][3] = filtrated.get(i).getCourseName();
            filtiredIn2D[i + 1][4] = filtrated.get(i).getInstructor();
            filtiredIn2D[i + 1][5] = filtrated.get(i).getDays();
            filtiredIn2D[i + 1][6] = filtrated.get(i).getTime();
            filtiredIn2D[i + 1][7] = filtrated.get(i).getLocation();
            filtiredIn2D[i + 1][8] = filtrated.get(i).getStatus();
            filtiredIn2D[i + 1][9] = filtrated.get(i).getWaitlist();
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
                addBtn.setOnAction(addingHandler);
                addBtn.setPrefWidth(70);
                addBtn.setFont((Font.font ("Verdana", 14)));
                grid.add(addBtn, filtiredIn2D[i].length, i);
                addButtons.add(addBtn); //Adding the button to the public list of buttons

                Button removeBtn = new Button("Remove");
                removeBtn.setPadding(new Insets(15, 15, 15, 15));
                removeBtn.setId(Integer.toString(i)); //Give each button its id
                removeBtn.setOnAction(removingHandler);
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

    public void fixAddRemoveButtons(ArrayList<String> arr) {
        for (int i = 0; i<arr.size(); i++) {
            removeButtons.get(Integer.parseInt(arr.get(i))).setDisable(false);
            addButtons.get(Integer.parseInt(arr.get(i))).setDisable(true);
        }
    }



    public ArrayList<Section> getFiltratedSections() {
        try {
            ArrayList<Section> filtrated = DataHandler.getAllowedSections();
            return filtrated;
        } catch (Exception e) {
            System.out.println("ERROR IN GETTING FILTRATED SECTIONS " + e.getMessage());
        }
        return filtrated;
    }




    class AddHandlerClass implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            int indexOfButton = Integer.parseInt(((Button) e.getTarget()).getId()) - 1;
            myBasket.addSection(filtrated.get(indexOfButton), indexOfButton, removeButtons, addButtons);
            clickedButtonsIDs.add(Integer.toString(indexOfButton));
        }
    }

    class RemoveHandlerClass implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            int indexOfButton = Integer.parseInt(((Button) e.getTarget()).getId()) - 1;
            myBasket.removeSection(filtrated.get(indexOfButton), indexOfButton, removeButtons, addButtons);
            clickedButtonsIDs.remove(Integer.toString(indexOfButton));
        }
    }
}