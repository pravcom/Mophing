package com.example.javafxcleancodedrawerver2.view;

import com.example.javafxcleancodedrawerver2.EventsView;
import com.example.javafxcleancodedrawerver2.Morphing;
import com.example.javafxcleancodedrawerver2.controller.MainController;
import com.example.javafxcleancodedrawerver2.model.MainModel;
import com.example.javafxcleancodedrawerver2.model.Shape;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainView extends BorderPane {
    private MainModel mainModel;
    private MainController mainController;
    private EventsView eventsView;
    private Morphing morphing;
    private List<Node> drawListNodes = new ArrayList<>();

    public void setEventsView(EventsView eventsView) {
        this.eventsView = eventsView;
    }

    public EventsView getEventsView() {
        return eventsView;
    }

    public MainView(MainModel mainModel, MainController mainController) {


        this.mainModel = mainModel;
        this.mainController = mainController;
        this.eventsView = EventsView.Draw;

        this.morphing = new Morphing();

        drawTop();

        createListners();
    }

    private void createListners() {
        //Рисуем фигуры с помощью линий
        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER -> {
                    if (this.eventsView == eventsView.Draw) {
                        pressEnter();
                    }
                }
            }
        });

        this.setOnMouseClicked(e -> {
            if (eventsView.equals(eventsView.Draw)) {
                mainController.getPointController().mouseClicked(e);

                List<Point2D> point2DList = mainModel.getPointModel().getPointList();

                System.out.println(point2DList);

                Point2D point = point2DList.get(point2DList.size() - 1);

                double x = point.getX();
                double y = point.getY();
                boolean shapeIsSelected = false;
//          выделяем фигуру
                if (mainModel.getShapeModel().getShapeList().size() > 0) {

                    List<Shape> shapeList = mainModel.getShapeModel().getShapeList();

                    for (int i = 0; i < shapeList.size(); i++) {
                        Shape shape = shapeList.get(i);

                        shapeIsSelected = shape.setShapeIsSelected(point);
                        if (shapeIsSelected == true) break;

                    }
                }
                if (shapeIsSelected == false)
                    this.getChildren().add(new Circle(x, y, 3));
                else
                    mainModel.getPointModel().clearPointList();
            } else if (eventsView.equals(eventsView.Morphing)) {

            }
        });

        mainModel.getMenuBarModel().getMenuList().forEach(m -> {
            m.getItems().forEach(i -> {
                switch (i.getText()) {
                    case "Morphing":
                        i.setOnAction(a -> {
                            drawListNodes = getChildren().stream().filter(f -> f instanceof Line).
                                    collect(Collectors.toList());
                            //Рисуем экран для Морфлинга
                            List<Shape> shapeList = mainModel.getShapeModel().getShapeList();
                            if (Morphing.shapeSelectedIsTwo(shapeList)) {
                                this.eventsView = eventsView.Morphing;
//
                                morphing.setShapes(shapeList);
                                this.setBottom(morphing.getSlider());
                                this.setRight(morphing.getSliderLabel());
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setContentText("Выделите две фигуры");
                                alert.show();
                            }
                        });
                        break;
                    case "Draw":
                        i.setOnAction(a -> {
                            this.setEventsView(eventsView.Draw);

                            clearShapes();
                            clearBottom();
                            clearRight();

                            drawListNodes.forEach(n -> {
                                getChildren().add(n);
                            });
                        });
                        break;
                    case "Clear":
                        i.setOnAction(a ->
                        {
                            clear();
                        });
                }
            });
        });
//Событие при изменения СЛАЙДЕРА
        morphing.getSlider().valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                //заполняем Label слайдера при изменени значений слайдера
                String sliderText = "Slider value: ";
                int sliderValue = t1.intValue();
                sliderText = sliderText + sliderValue;
                morphing.getSliderLabel().setText(sliderText);

                //Будет логика морфлинга
                //удаляем фигуру пред результата морфинга с экрана
                if (morphing.getOldShape() != null) {
                    for (Line line : morphing.getOldShape()) {
                        getChildren().remove(line);
                    }
                }

                Shape shape = new Shape();
                shape.addLine(morphing.morph(sliderValue));
                morphing.setOldShape(shape.getLineList());

                getChildren().addAll(shape.getLineList());
            }
        });
    }

    private void clearShapes() {
        List<Node> nodeList = new ArrayList<>();

        nodeList = getChildren().stream().filter(e -> e instanceof Line).collect(Collectors.toList());

        nodeList.forEach(e ->
        {
            getChildren().remove(e);
        });

    }

    private void drawShapes() {
        mainModel.getShapeModel().getShapeList().forEach(s -> {
            getChildren().addAll(s.getLineList());
        });
    }

    private void drawTop() {
        HBox hBox = new HBox(mainModel.getMenuBarModel());
        this.setTop(hBox);
    }

    private void clearBottom() {
        List<Node> nodeList = new ArrayList<>();

        nodeList = getChildren().stream().filter(e -> e == getBottom()).collect(Collectors.toList());

        nodeList.stream().forEach(e ->
        {
            getChildren().remove(e);
        });

    }

    private void clearRight() {
        List<Node> nodeList = new ArrayList<>();

        nodeList = getChildren().stream().filter(e -> e == getRight()).collect(Collectors.toList());

        nodeList.stream().forEach(e ->
        {
            getChildren().remove(e);
        });
    }

    private void clear() {
        List<Node> nodeList = new ArrayList<>();

        nodeList = getChildren().stream().filter(e ->
                (e instanceof Line ||
                        e instanceof Circle)).collect(Collectors.toList());

        nodeList.stream().forEach(e ->
        {
            getChildren().remove(e);
        });
        drawListNodes.clear();
        mainModel.getShapeModel().clearShapeList();
        mainModel.getPointModel().clearPointList();
    }

    private void pressEnter() {
        System.out.println("--------------------");
        mainController.getShapeController().setOnEnterPressed(mainModel.getPointModel().getPointList());

        List<Shape> shapeList = mainModel.getShapeModel().getShapeList();

        Shape shape = shapeList.get(shapeList.size() - 1);

        this.getChildren().addAll(shape.getLineList());

        mainModel.getPointModel().clearPointList();
    }
}
