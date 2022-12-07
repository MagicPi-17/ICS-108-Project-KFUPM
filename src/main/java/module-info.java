module com.example.termproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.termproject to javafx.fxml;
    exports com.example.termproject;
}