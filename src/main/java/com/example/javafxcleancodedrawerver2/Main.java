package com.example.javafxcleancodedrawerver2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main extends Application {
    Pane pane = new Pane();
    double width = 400;
    double height = 400;
    double cX = width / 2;
    double cY = height / 2;

    @Override/*from   www .  j a va 2s  . co m*/
    public void start(Stage primaryStage) {
        pane.setOnKeyPressed((KeyEvent e) -> {
            switch (e.getCode()) {
                case UP: moveUp(); break;
                case DOWN: moveDown(); break;
                case LEFT: moveLeft(); break;
                case RIGHT: moveRight(); break;
            }
        });

        primaryStage.setScene(new Scene(pane, width, height));
        primaryStage.setTitle("java2s.com");
        primaryStage.show();
        pane.requestFocus();
    }

    private void moveUp() {
        pane.getChildren().add(new Line(cX, cY, cX, cY - 10));
        cY -= 10;

    }

    private void moveDown() {
        pane.getChildren().add(new Line(cX, cY, cX, cY + 10));
        cY += 10;

    }
    private void moveLeft() {
        pane.getChildren().add(new Line(cX, cY, cX - 10, cY));
        cX -= 10;

    }
    private void moveRight() {
        pane.getChildren().add(new Line(cX, cY, cX + 10, cY));
        cX += 10;
    }



    public static void main(String[] args) {
        Application.launch(args);
    }
}