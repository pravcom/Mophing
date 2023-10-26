package com.example.javafxcleancodedrawerver2;

import com.example.javafxcleancodedrawerver2.controller.MainController;
import com.example.javafxcleancodedrawerver2.model.MainModel;
import com.example.javafxcleancodedrawerver2.view.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        MainModel mainModel = new MainModel();
        MainController mainController = new MainController(mainModel);
        MainView mainView = new MainView(mainModel, mainController);


        Scene scene = new Scene(mainView, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        mainView.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}