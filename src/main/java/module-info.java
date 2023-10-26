module com.example.javafxcleancodedrawerver2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxcleancodedrawerver2 to javafx.fxml;
    exports com.example.javafxcleancodedrawerver2;
    exports com.example.javafxcleancodedrawerver2.view;
    opens com.example.javafxcleancodedrawerver2.view to javafx.fxml;
    exports com.example.javafxcleancodedrawerver2.model;
    opens com.example.javafxcleancodedrawerver2.model to javafx.fxml;
    exports com.example.javafxcleancodedrawerver2.controller;
    opens com.example.javafxcleancodedrawerver2.controller to javafx.fxml;
}